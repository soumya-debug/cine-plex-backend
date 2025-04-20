package com.movieplan.service.impl;

import com.movieplan.dto.TheaterDTO;
import com.movieplan.model.Movie;
import com.movieplan.model.Theater;
import com.movieplan.repository.MovieRepository;
import com.movieplan.repository.theaterRepository;
import com.movieplan.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    private theaterRepository tRepo;

    @Autowired
    private MovieRepository mRepo;

    @Override
    public Optional<Theater> getTheaterById(long id) {
        return tRepo.findById(id);
    }

    @Override
    public List<Theater> getAllTheaters() {
        return tRepo.findAll();
    }

    @Override
    public List<Theater> getTheatersByMovieId(long movieId) {
        Movie movie = mRepo.getOne(movieId);
        return tRepo.getTheatersByMovie(movie);
    }

    @Override
    public Map<String, Object> addTheater(TheaterDTO dto) {
        Movie movie = mRepo.getOne(dto.getMovieId());
        Theater theater = new Theater();
        theater.setId(0);  // Setting the ID to 0 ensures Hibernate generates a new ID
        theater.setMovie(movie);
        theater.setTheatreName(dto.getTheatreName());
        theater.setTheatreAddress(dto.getTheatreAddress());
        tRepo.save(theater);

        return Map.of("text", "Successfully added");
    }

    @Override
    public Map<String, Object> updateTheater(long theaterId, TheaterDTO dto) {
        Movie movie = mRepo.getOne(dto.getMovieId());
        Theater theater = new Theater();
        theater.setId(theaterId);
        theater.setTheatreName(dto.getTheatreName());
        theater.setTheatreAddress(dto.getTheatreAddress());
        theater.setMovie(movie);
        tRepo.save(theater);

        return Map.of("text", "Successfully edited");
    }

    @Override
    public Map<String, Object> deleteTheater(long theaterId) {
        tRepo.deleteById(theaterId);
        return Map.of("text", "Deleted Theater Id " + theaterId);
    }
}
