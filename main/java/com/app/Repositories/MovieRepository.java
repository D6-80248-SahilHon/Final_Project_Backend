package com.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String name);
    
    @Query("select m from Movie m left join fetch m.shows where m.id=:id")
    Movie getMovieDetailsById(@Param("id") Integer id);
   
}
