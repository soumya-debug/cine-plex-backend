package com.movieplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplan.model.MovieShow;

public interface ShowRepository extends JpaRepository<MovieShow, Long>{

}
