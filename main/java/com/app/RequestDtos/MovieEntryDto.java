package com.app.RequestDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.types.Genre;
import com.app.types.Language;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntryDto {
    private String movieName;
    private Integer duration;
    private Double rating;
    private LocalDate releaseDate;
    private Genre genre;
    private Language language;
}
