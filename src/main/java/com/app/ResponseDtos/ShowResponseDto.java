package com.app.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.models.Movie;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDto {
	private Integer showId;
	
	private Time showStartTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate showDate;
	
	private Integer theaterId;
	private Integer movieId;
	@Override
	public String toString() {
		return "ShowResponseDto [showId=" + showId + ", showStartTime=" + showStartTime + ", showDate=" + showDate
				+ ", theaterId=" + theaterId + ", movieId=" + movieId + "]";
	}
		
	
}



