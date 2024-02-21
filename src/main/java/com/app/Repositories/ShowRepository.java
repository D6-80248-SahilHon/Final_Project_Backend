package com.app.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.models.Show;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query("select s.time from Show s where s.date=:date and s.theater.id=:theaterId and s.movie.id=:movieId")
	public List<Time> getShowTimingsOnDate(LocalDate date,Integer theaterId,Integer movieId);

    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1" , nativeQuery = true)
    public Integer getMostShowsMovie();

    @Query(value = "select s from Show s where s.movie.id = :movieId" )
    public List<Show> getAllShowsOfMovie(@Param("movieId")Integer movieId);
    
    @Query(value = "select * from shows where theater_id = :theaterId" , nativeQuery = true)
    public List<Show> getAllShowsOfTheater(@Param("theaterId")Integer theaterId);
}
