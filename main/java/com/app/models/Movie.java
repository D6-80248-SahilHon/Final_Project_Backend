package com.app.models;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.types.Genre;
import com.app.types.Language;

import lombok.*;

@Entity
@Table(name = "MOVIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String movieName;

    private Integer duration;

    @Column(scale = 2)
    private Double rating;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();
    
    @Lob
	private byte[] image;
    
//    private String imagePath;
    

}

