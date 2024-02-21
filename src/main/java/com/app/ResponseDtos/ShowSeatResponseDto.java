package com.app.ResponseDtos;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.types.SeatType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatResponseDto {
		private Integer id;
	    private String seatNo;

	    @Enumerated(value = EnumType.STRING)
	    private SeatType seatType;

	    private Integer price;
	    private Boolean isAvailable;
	    private Boolean isFoodContains;
}