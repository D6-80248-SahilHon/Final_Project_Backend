package com.app.ResponseDtos;


import java.time.LocalDate;

import com.app.types.Genre;
import com.app.types.Language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponseDto {
	private Integer Id;
	
	 private String movieName;
	 
	 private Integer duration;
	 
	 private LocalDate releaseDate;
	 
	 private Double rating;
	 
	 private Genre genre;
	 
	 private Language language;
	
}
