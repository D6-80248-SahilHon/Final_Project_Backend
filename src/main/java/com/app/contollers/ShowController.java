package com.app.contollers;

import com.app.RequestDtos.ShowEntryDto;
import com.app.RequestDtos.ShowSeatEntryDto;
import com.app.RequestDtos.ShowTimingsDto;
import com.app.ResponseDtos.ShowResponseDto;
import com.app.ResponseDtos.ShowSeatResponseDto;
import com.app.services.ShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/show")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto) {
    	System.out.println("hello in show controller");
        try {
            ShowSeatEntryDto result = showService.addShow(showEntryDto);
            String s=showService.associateShowSeats(result);
            return new ResponseEntity<>(s, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/associateSeats")
//    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatEntryDto showSeatEntryDto) {
//        try {
//            String result = showService.associateShowSeats(showSeatEntryDto);
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/showTimingsOnDate")
    public ResponseEntity<List<Time>> showTimingsOnDate( ShowTimingsDto showTimingsDto) {
//    	System.out.println(showTimingsDto.getMovieId());
        System.out.println(showTimingsDto.getShowDate());
        try {
            List<Time> result = showService.showTimingsOnDate(showTimingsDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movieHavingMostShows")
    public ResponseEntity<String> movieHavingMostShows() {
        try {
            String movie = showService.movieHavingMostShows();
            return new ResponseEntity<>(movie, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

     @GetMapping("/movie-shows/{id}")
    public ResponseEntity<List<ShowResponseDto>> getMovieShows(@PathVariable Integer id) {
        try {
        	
        	
        	List<ShowResponseDto> lshow = showService.getShowByMovieid(id);
            return new ResponseEntity<>(lshow, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
     
     @GetMapping("/getShowSeatList/{id}")
     public List<ShowSeatResponseDto> getShowSeatList(@PathVariable Integer id){
     	
     	return showService.getShowSeatList(id); //error handling is pending
     }
     
     
     
     
     @GetMapping("/theater-shows/{id}")
     public ResponseEntity<List<ShowResponseDto>> getTheaterShows(@PathVariable Integer id) {
         try {
         	List<ShowResponseDto> lshow = showService.getShowByTheaterid(id);
             return new ResponseEntity<>(lshow, HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
         }
     }
     
     @DeleteMapping("/deleteShow/{id}")
     public ResponseEntity<String> deleteShow(@PathVariable Integer id) {//laksh
         try {
         	String s=showService.deleteShow(id);
             return new ResponseEntity<>(s, HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity<>("SomeThing Went Wrong!!!", HttpStatus.NOT_FOUND);
         }
     }
}
