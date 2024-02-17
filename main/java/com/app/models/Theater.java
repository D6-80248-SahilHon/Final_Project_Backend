package com.app.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.types.Genre;
import com.app.types.Language;

import lombok.*;

@Entity
@Table(name = "THEATERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Show> showList = new ArrayList<>();
    

	
	
	
    
    
}
