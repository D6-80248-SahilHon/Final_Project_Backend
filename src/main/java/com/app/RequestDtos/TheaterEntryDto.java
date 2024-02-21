package com.app.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterEntryDto {

    private String name;
    
    private String address;	
    
    private Integer noOfSeatInRow;
    
    private Integer noOfPremiumSeat;
    
    private Integer noOfClassicSeat;
    
    
}
