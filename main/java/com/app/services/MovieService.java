package com.app.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Exceptions.MovieAlreadyPresentWithSameNameAndLanguage;
import com.app.Exceptions.MovieDoesNotExists;
import com.app.Repositories.MovieRepository;
import com.app.Repositories.ShowRepository;
import com.app.RequestDtos.MovieEntryDto;
import com.app.ResponseDtos.MovieResponseDto;
import com.app.Transformers.MovieTransformer;
import com.app.models.Movie;
import com.app.models.Show;
import com.app.models.Ticket;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;
    
    @Autowired
    private ModelMapper mapper;

    public String addMovie(MovieEntryDto movieEntryDto) throws MovieAlreadyPresentWithSameNameAndLanguage {
        if(movieRepository.findByMovieName(movieEntryDto.getMovieName()) != null) {
            if(movieRepository.findByMovieName(movieEntryDto.getMovieName()).getLanguage().equals(movieEntryDto.getLanguage())){
                throw new MovieAlreadyPresentWithSameNameAndLanguage();
            }
        }
        Movie movie = MovieTransformer.movieDtoToMovie(movieEntryDto);
        movieRepository.save(movie);
        return "The movie has been added successfully";
    }

    public Long totalCollection(Integer movieId) throws MovieDoesNotExists {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if(movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }
        List<Show> showListOfMovie = showRepository.getAllShowsOfMovie(movieId);
        long ammount = 0;
        for(Show show : showListOfMovie) {
            for(Ticket ticket : show.getTicketList()) {
                ammount += (long)ticket.getTotalTicketsPrice();
            }
        }
        return ammount;
    }

	public List<MovieResponseDto> getMoviesList() {
		List<Movie> list=movieRepository.findAll();
		List<MovieResponseDto> rlist=MovieTransformer.movieToMovieDto(list);
		return rlist;
	}
	
	public MovieResponseDto getMoviesById(Integer id) {
		MovieResponseDto mdto=null;
		try {
			
		Movie mo=movieRepository.findById(id).orElseThrow();
		 mdto=mapper.map(mo, MovieResponseDto.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return mdto;
	}

	public String deleteMovie(Integer movieId) throws MovieDoesNotExists {
		  Optional<Movie> movieOpt = movieRepository.findById(movieId);
	        if(movieOpt.isEmpty()) {
	            throw new MovieDoesNotExists();
	        }
	      movieRepository.deleteById(movieId);  
	       
		return "Movie Deleted Successfully";
	}
	
	
}
