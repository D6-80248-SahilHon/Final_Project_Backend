package com.app.ResponseDtos;

import java.util.List;

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
public class TheaterResponseDto {
	//edited by sasawt feel free to improvise 
	private Integer id;

    private String name;

    private String address;
    
    private List<TheaterSeatDto> theaterseatlist; 

}
