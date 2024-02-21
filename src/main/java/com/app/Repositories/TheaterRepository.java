package com.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.models.Theater;
import com.app.models.TheaterSeat;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
	@Query("select t from Theater t left join fetch t.theaterSeatList where t.address=:address")
    Theater getTheaterAndTheaterSeatDetails(@Param("address") String address);
	
	@Query("select t from Theater t left join fetch t.showList where t.id=:id")
	Theater getTheaterDetailsForShow(@Param("id") Integer id);
	
	@Query("select t from Theater t left join fetch t.theaterSeatList where t.id=:id")
	Theater getTheaterAndTheaterSeatsById(@Param("id") Integer id);
	
//	@Query("select t.theaterSeatList from Theater t left join fetch t.theaterSeatList where t.id=:id")
//	List<TheaterSeat> getTheaterSeatListDetails(@Param("id") Integer id);
}
