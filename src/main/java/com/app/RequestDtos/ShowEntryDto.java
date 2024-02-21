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

import com.fasterxml.jackson.annotation.JsonFormat;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowEntryDto {

	@JsonFormat(pattern="HH:mm:ss")
    private Time showStartTime;
    private LocalDate showDate;
    private Integer theaterId;
    private Integer movieId;
    private Integer priceOfClassicSeat;
    private Integer priceOfPremiumSeat;
    
}
