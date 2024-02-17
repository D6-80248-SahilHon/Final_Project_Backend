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

//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketResponseDto {
    private Time time;
    private LocalDate date;
    private String movieName;
    private String theaterName;
    private String address;
    private String bookedSeats;
    private Integer totalPrice;
}
