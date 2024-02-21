package com.app.contollers;


import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.RequestDtos.MovieEntryDto;
import com.app.ResponseDtos.MovieResponseDto;
import com.app.models.Movie;
import com.app.services.ImageHandlingService;
import com.app.services.MovieService;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ImageHandlingService imgService;

    @PostMapping("/addNew")
    public ResponseEntity<Integer> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        try {
        	Movie movie= movieService.addMovie(movieEntryDto);
            Integer result =movie.getId();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalCollection/{movieId}")
    public ResponseEntity<Long> totalCollection(@PathVariable Integer movieId) {
        try {
            Long result = movieService.totalCollection(movieId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    

    @PostMapping(value = "/images/{movieId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable Integer movieId, @RequestPart MultipartFile imageFile)
			throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(imgService.uploadImage(movieId, imageFile));
	}
    
    @GetMapping(value = "/images/{movieId}")
	public ResponseEntity<byte[]> serveMovieImage(@PathVariable Integer movieId) throws IOException {
        byte[] base64encodedData = Base64.getEncoder().encode(imgService.downloadImage(movieId));
        System.out.println(base64encodedData);
		return ResponseEntity.ok(base64encodedData);
	}
    

    
    //Error handling is Pending
    @GetMapping(value="/MovieList")
    public List<MovieResponseDto> movieList(){
    	return movieService.getMoviesList();
    }
    
    @GetMapping(value="/{id}")
    public MovieResponseDto movieById(@PathVariable Integer id){
    	return movieService.getMoviesById(id);
    }
    
    @DeleteMapping("{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer movieId){
    	try {
    		String message= movieService.deleteMovie(movieId);
    		return new ResponseEntity<String>(message,HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Movie Cannot be Deleted",HttpStatus.BAD_REQUEST);
    	}
    	
    }

}
