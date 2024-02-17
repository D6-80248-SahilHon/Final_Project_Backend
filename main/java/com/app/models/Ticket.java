package com.app.models;

import org.hibernate.annotations.CreationTimestamp;

import com.app.types.Genre;
import com.app.types.Language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class Ticket {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer ticketId;
	    private Integer totalTicketsPrice;
	    private String bookedSeats;

	    @CreationTimestamp
	    private LocalDate bookedAt;

	    @ManyToOne
	    @JoinColumn
	    private Show show;

	    @ManyToOne
	    @JoinColumn
	    private User user;
	    	    
}
