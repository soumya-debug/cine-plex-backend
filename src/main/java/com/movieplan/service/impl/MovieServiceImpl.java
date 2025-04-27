package com.movieplan.service.impl;

import com.movieplan.model.Movie;
import com.movieplan.repository.MovieRepository;
import com.movieplan.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = getMovieById(id);
        existingMovie.setName(updatedMovie.getName());
        existingMovie.setDuration(updatedMovie.getDuration());
        existingMovie.setLanguage(updatedMovie.getLanguage());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setBanner(updatedMovie.getBanner());
        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}

