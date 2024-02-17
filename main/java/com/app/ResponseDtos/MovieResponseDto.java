package com.app.ResponseDtos;

import com.app.types.Gender;
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
	 private String movieName;
	 
	 private Integer duration;
	 
	 private Double rating;
	 
	 private Genre genre;
	 
	 private Language language;
	
}
