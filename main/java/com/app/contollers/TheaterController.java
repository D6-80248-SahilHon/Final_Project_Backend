package com.app.contollers;

import com.app.RequestDtos.TheaterEntryDto;
import com.app.RequestDtos.TheaterSeatEntryDto;
import com.app.services.TheaterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
@CrossOrigin(origins = "http://localhost:3000")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto) {
        try {
            String result = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatEntryDto entryDto) {
        try {
            String result = theaterService.addTheaterSeat(entryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteTheater/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Integer id){
    	String message="";
    	try {
    		message = theaterService.deleteTheater(id);
    		return new ResponseEntity<>(message,HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    	}
    }
}
