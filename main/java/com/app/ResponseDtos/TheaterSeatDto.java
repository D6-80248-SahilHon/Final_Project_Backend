package com.app.ResponseDtos;

import com.app.types.SeatType;

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
public class TheaterSeatDto {
	
    private Integer id;
    private String seatNo;
    private SeatType seatType;
    private Integer theaterId;
}
