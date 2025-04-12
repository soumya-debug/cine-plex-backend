package com.movieplan.service.impl;

import com.movieplan.dto.MovieShowDTO;
import com.movieplan.model.Movie;
import com.movieplan.model.MovieShow;
import com.movieplan.repository.MovieRepository;
import com.movieplan.repository.showRepository;
import com.movieplan.service.MovieShowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieShowServiceImpl implements MovieShowService {

    private final showRepository sRepo;
    private final MovieRepository mRepo;

    public MovieShowServiceImpl(showRepository sRepo, MovieRepository mRepo) {
        this.sRepo = sRepo;
        this.mRepo = mRepo;
    }

    @Override
    public MovieShowDTO getShowById(long showId) {
        Optional<MovieShow> optionalShow = sRepo.findById(showId);
        return optionalShow.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<MovieShowDTO> getAllShows() {
        List<MovieShow> shows = sRepo.findAll();
        return shows.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllActiveMovies() {
        return mRepo.getMoviesByTheater();
    }

    private MovieShowDTO convertToDTO(MovieShow show) {
        return new MovieShowDTO(show.getId(), show.getStatus());
    }
}

