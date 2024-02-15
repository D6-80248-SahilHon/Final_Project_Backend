package com.app.contollers;

import com.app.Repositories.UserRepository;
import com.app.RequestDtos.SigninRequest;
import com.app.RequestDtos.UserEntryDto;
import com.app.ResponseDtos.ReturnUserDto;
import com.app.ResponseDtos.SigninResponse;
import com.app.ResponseDtos.TicketResponseDto;
import com.app.models.User;
import com.app.security.JwtUtils;
import com.app.services.UserService;

import io.jsonwebtoken.Claims;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager mgr;
    
    @Autowired
    JwtUtils utils;
    
    @Autowired
    ModelMapper mapper;

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            String result = userService.addUser(userEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allTickets/{userId}")
    public ResponseEntity<List<TicketResponseDto>> allTickets(@PathVariable Integer userId) {
        try {
        	System.out.println(userId);
            List<TicketResponseDto> result = userService.allTickets(userId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
    	try {
    		userService.deleteUser(userId);
    		return new ResponseEntity<String>("User Deleted Successfully",HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>("User Not found",HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody SigninRequest reqDto ){
    	System.out.println("here "+reqDto);
    	
    	Authentication verifiedAuth=mgr
    			.authenticate(new UsernamePasswordAuthenticationToken(reqDto.getEmailId(), reqDto.getPassword()));
    	System.out.println(verifiedAuth.getClass());
    	System.out.println(verifiedAuth.isAuthenticated());
    	
    	if(verifiedAuth.isAuthenticated()) {
    		return ResponseEntity
    				.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth),"Successful Authentication"));
    		
    	}
    	
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credenstials..");
    }
    
    @GetMapping()
    public List<ReturnUserDto> getAllUser(){
    	try {
    		
    		return userService.getAllUser();
    	}catch(Exception e){
    		return null;
    	}
    }

    @GetMapping("/email")
    public ReturnUserDto getUserByEmail(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Extract JWT from the Authorization header
                String jwt = authHeader.substring(7);
                
                // Validate JWT and retrieve payload claims
                Claims payloadClaims = utils.validateJwtToken(jwt);
                
                // Extract user email from the claims
                String email = utils.getUserNameFromJwtToken(payloadClaims);
                System.out.println(email);
                
                // Retrieve user information by email
                return mapper.map(userService.getUserByEmail(email), ReturnUserDto.class);
            }
        } catch (Exception e) {
            // Handle any exceptions and return null
            return null;
        }
        return null;
    }


    
    @PostMapping("/validate")
    public String validateUser(@RequestParam("userEmail") String userEmail,@RequestParam("password") String password, HttpServletRequest request, HttpSession session) {
    	User u=userService.getByEmailPassword(userEmail,password);
    	if(u!=null) {
    	    HttpSession newSession = request.getSession(); // create session
    	    newSession.setAttribute("User",u);
    	    return "Success";
    	}else {
    		return "User not valid ";
    	}
    	
    }
}
