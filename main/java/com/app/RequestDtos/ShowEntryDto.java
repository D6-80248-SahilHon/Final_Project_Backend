package com.app.RequestDtos;

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

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowEntryDto {

    private Time showStartTime;
    private LocalDate showDate;
    private Integer theaterId;
    private Integer movieId;
    
//	public Time getShowStartTime() {
//		return showStartTime;
//	}
//	public void setShowStartTime(Time showStartTime) {
//		this.showStartTime = showStartTime;
//	}
//	public LocalDate getShowDate() {
//		return showDate;
//	}
//	public void setShowDate(LocalDate showDate) {
//		this.showDate = showDate;
//	}
//	public Integer getTheaterId() {
//		return theaterId;
//	}
//	public void setTheaterId(Integer theaterId) {
//		this.theaterId = theaterId;
//	}
//	public Integer getMovieId() {
//		return movieId;
//	}
//	public void setMovieId(Integer movieId) {
//		this.movieId = movieId;
//	}
//    
    
}
