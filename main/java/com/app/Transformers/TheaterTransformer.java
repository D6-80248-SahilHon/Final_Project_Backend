package com.app.Transformers;


import java.util.ArrayList;
import java.util.List;

import com.app.RequestDtos.TheaterEntryDto;
import com.app.ResponseDtos.TheaterResponseDto;
import com.app.models.Theater;

public class TheaterTransformer {

    public static Theater theaterDtoToTheater(TheaterEntryDto entryDto) {
        Theater theater = Theater.builder()
                .name(entryDto.getName())
                .address(entryDto.getAddress())
                .build();
        return theater;
    }
    
    public static List<TheaterResponseDto> theaterToTheaterDto(List<Theater> list)
    {
    	List<TheaterResponseDto> responseList=new ArrayList<TheaterResponseDto>();
    	for(Theater theater:list) {
    		TheaterResponseDto theaterResponseDto=TheaterResponseDto.builder()
    				.id(theater.getId())
    				.name(theater.getName())
    				.address(theater.getAddress())
    				.theaterseatlist(TheaterSeatTransformer.theaterSeattoTheaterSeatDto(theater.getTheaterSeatList()))
    				.build();
    		responseList.add(theaterResponseDto);
    	}
    	return responseList;
    }
}
