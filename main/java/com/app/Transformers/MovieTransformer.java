package com.app.Transformers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.RequestDtos.MovieEntryDto;
import com.app.ResponseDtos.MovieResponseDto;
import com.app.models.Movie;
import com.app.utils.ImageUtil;

public class MovieTransformer {
	
	
	
    public static Movie movieDtoToMovie(MovieEntryDto movieEntryDto) {
        Movie movie = Movie.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .releaseDate(movieEntryDto.getReleaseDate())
                .rating(movieEntryDto.getRating())
                .build();
     

        return movie;
    }
    
    public static List<MovieResponseDto> movieToMovieDto(List<Movie> list){
    	List<MovieResponseDto> responseList=new ArrayList<MovieResponseDto>();
    	for (Movie movie : list) {
    		MovieResponseDto movieResponseDto= MovieResponseDto.builder()
    				.movieName(movie.getMovieName())
                    .duration(movie.getDuration())
                    .genre(movie.getGenre())
                    .language(movie.getLanguage())
                    .rating(movie.getRating())
                    .build();
    			responseList.add(movieResponseDto);
		}
		return responseList;
    	
    }
}
