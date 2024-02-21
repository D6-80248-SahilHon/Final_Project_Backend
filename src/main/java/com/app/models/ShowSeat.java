package com.app.models;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.app.types.Genre;
import com.app.types.Language;
import com.app.types.SeatType;

import lombok.*;

@Entity
@Table(name = "SHOW_SEATS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;
    private Boolean isAvailable;
    private Boolean isFoodContains;

    @ManyToOne
    @JoinColumn
    private Show show;
    

    
}
