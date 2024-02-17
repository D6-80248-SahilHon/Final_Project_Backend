package com.app.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.Repositories.MovieRepository;

import com.app.models.Movie;

@Service
@Transactional
public class ImageHandlingService {
	@Autowired
	private MovieRepository movieRepo;
	
	
	public String uploadImage(Integer movieId, MultipartFile image) throws IOException {
		// get emp from emp id
		Optional<Movie> movieOpt = movieRepo.findById(movieId);
		Movie movie=movieOpt.get();;
		// emp found --> PERSISTENT
		// to store the img directly in DB as a BLOB
		movie.setImage(image.getBytes());
		return "Image File Uploaded Successfully";
	}
	
	
	public byte[] downloadImage(Integer movieId) throws IOException {
		// get Movie by id
		Movie movie = movieRepo.findById(movieId).get();
		// Movie found --> PERSISTENT
		return movie.getImage();
	}
}
