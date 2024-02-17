package com.app.Transformers;

import java.util.ArrayList;
import java.util.List;

import com.app.ResponseDtos.TheaterSeatDto;
import com.app.models.TheaterSeat;

public class TheaterSeatTransformer {

	
	public static List<TheaterSeatDto> theaterSeattoTheaterSeatDto(List<TheaterSeat> theaterseatlist)
	{//edited by saswat feel free to improvise
		List<TheaterSeatDto> responseList=new ArrayList<TheaterSeatDto>();
		for(TheaterSeat theaterseat:theaterseatlist ) {
		TheaterSeatDto theaterseatdto=TheaterSeatDto.builder()
				.id(theaterseat.getId())
				.seatNo(theaterseat.getSeatNo())
				.seatType(theaterseat.getSeatType())
				.theaterId(theaterseat.getTheater().getId())
				.build();
		responseList.add(theaterseatdto);	
		}
		return responseList;
	}
	
	
	
}
