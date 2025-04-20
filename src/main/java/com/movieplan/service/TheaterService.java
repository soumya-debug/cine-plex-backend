package com.movieplan.service;

import com.movieplan.dto.TheaterDTO;
import com.movieplan.model.Theater;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TheaterService {
    Optional<Theater> getTheaterById(long id);
    List<Theater> getAllTheaters();
    List<Theater> getTheatersByMovieId(long movieId);
    Map<String, Object> addTheater(TheaterDTO dto);
    Map<String, Object> updateTheater(long theaterId, TheaterDTO dto);
    Map<String, Object> deleteTheater(long theaterId);
}

