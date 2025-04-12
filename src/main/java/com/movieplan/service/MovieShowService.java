package com.movieplan.service;

import com.movieplan.dto.MovieShowDTO;
import com.movieplan.model.Movie;

import java.util.List;

public interface MovieShowService {

    MovieShowDTO getShowById(long showId);

    List<MovieShowDTO> getAllShows();

    List<Movie> getAllActiveMovies();
}

