// This interface is a Spring Data JPA repository for accessing Movie entities.
package com.movieplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movieplan.model.Movie;

// This interface extends JpaRepository to provide common CRUD operations for Movie entities.
public interface movieRepository extends JpaRepository<Movie, Long> {

    // Custom query using JPQL to retrieve movies associated with theaters
    @Query("SELECT distinct m FROM Movie m join Theater t on t.movie = m")
    List<Movie> getMoviesByTheater();

    // Method to find a Movie by its name
    Movie findByName(String name);
}
