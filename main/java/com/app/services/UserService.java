package com.app.services;

import com.app.Exceptions.UserAlreadyExistsWithEmail;
import com.app.Exceptions.UserDoesNotExists;
import com.app.Repositories.UserRepository;
import com.app.RequestDtos.SigninRequest;
import com.app.RequestDtos.UserEntryDto;
import com.app.ResponseDtos.ReturnUserDto;
import com.app.ResponseDtos.TicketResponseDto;
import com.app.Transformers.TicketTransformer;
import com.app.Transformers.UserTransformer;
import com.app.models.Ticket;
import com.app.models.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder enc;
    
    @Autowired
    ModelMapper mapper;

    public String addUser(UserEntryDto userEntryDto) throws UserAlreadyExistsWithEmail{
//    	System.out.println(userRepository.findByEmailId(userEntryDto.getEmailId()));
    	Optional<User> useropt=userRepository.findByEmailId(userEntryDto.getEmailId());
        if(!useropt.isEmpty()) {
            throw new UserAlreadyExistsWithEmail();
        }
        User user = UserTransformer.userDtoToUser(userEntryDto);
        user.setPassword(enc.encode(userEntryDto.getPassword()));

        userRepository.save(user);
        return "User Saved Successfully";
    }

    public List<TicketResponseDto> allTickets(Integer userId) throws UserDoesNotExists{
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserDoesNotExists();
        }
        User user = userOpt.get();
        List<Ticket> ticketList = user.getTicketList();
//        System.out.println(ticketList.get(1).getTicketId());
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            TicketResponseDto ticketResponseDto = TicketTransformer.returnTicket(ticket.getShow(), ticket);
            ticketResponseDtoList.add(ticketResponseDto);
        }
        System.out.println(ticketResponseDtoList.toString());
        return ticketResponseDtoList;
    }
    
    public User getByEmailPassword(SigninRequest uservaldto) {
		
		User u=userRepository.findByEmailId(uservaldto.getEmailId()).orElseThrow();
		if(u.getPassword().equals(uservaldto.getPassword()))return u;
		return null;
	}

	public void deleteUser(Integer userId) {
		Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserDoesNotExists();
        }
		userRepository.deleteById(userId);
	}
	
	public List<ReturnUserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> li=userRepository.findAll();
		List<ReturnUserDto> lst=new ArrayList<ReturnUserDto>();
		for(User u:li) {
			lst.add(mapper.map(u, ReturnUserDto.class));
		}
		return lst;
	}
	
	public User getByEmailPassword(String userEmail, String password) {
		User u=userRepository.findByEmailId(userEmail).orElseThrow();
		if(u.getPassword().equals(password))return u;
		return null;
	}

	//
	public User getUserByEmail(String email) {
		User u= userRepository.findByEmailId(email).get();
		u.getTicketList().size();
		return  u;
	}
}
