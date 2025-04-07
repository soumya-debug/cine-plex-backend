package com.movieplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.TheaterController;
import com.movieplan.model.Movie;
import com.movieplan.model.Theater;
import com.movieplan.repository.movieRepository;
import com.movieplan.repository.theaterRepository;

class TheaterControllerTest {

    private TheaterController theaterController;
    private theaterRepository tRepo;
    private movieRepository mRepo;

    @BeforeEach
    void setUp() {
        tRepo = mock(theaterRepository.class);
        mRepo = mock(movieRepository.class);
        theaterController = new TheaterController();
        injectMockRepositories(theaterController, tRepo, mRepo);
    }

    @Test
    void testGetTheater() {
        long theaterId = 1;
        Theater theater = new Theater();

        when(tRepo.findById(theaterId)).thenReturn(Optional.of(theater));

        Optional<Theater> result = theaterController.getMovie(theaterId);

        assertEquals(Optional.of(theater), result);
    }

    @Test
    void testGetAllTheaters() {
        List<Theater> theaters = new ArrayList<>();
        theaters.add(new Theater());
        theaters.add(new Theater());

        when(tRepo.findAll()).thenReturn(theaters);

        List<Theater> result = theaterController.getAllMovies();

        assertEquals(2, result.size());
    }

    @Test
    void testGetAllTheatersByMovieId() {
        long movieId = 1;
        Movie movie = new Movie();
        List<Theater> theaters = new ArrayList<>();
        theaters.add(new Theater());
        theaters.add(new Theater());

        when(mRepo.getOne(movieId)).thenReturn(movie);
        when(tRepo.getTheatersByMovie(movie)).thenReturn(theaters);

        ResponseEntity<Map<String, Object>> response = theaterController.getAllTheatersByMovieId(movieId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(theaters, response.getBody().get("text"));
    }

    @Test
    void testEditTheater() {
        long theaterId = 1;
        long movieId = 2;
        Theater theater = new Theater();
        theater.setId(theaterId);

        when(mRepo.getOne(movieId)).thenReturn(new Movie());
        when(tRepo.save(any(Theater.class))).thenReturn(theater);

        Theater updatedTheater = new Theater();
        ResponseEntity<Map<String, Object>> response = theaterController.editMovie(theaterId, movieId, updatedTheater);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully edited", response.getBody().get("text"));
    }

    
    // Method to inject the mock repositories using reflection
    private void injectMockRepositories(TheaterController controller, theaterRepository theaterRepo,
            movieRepository movieRepo) {
        try {
            Field theaterRepoField = controller.getClass().getDeclaredField("tRepo");
            Field movieRepoField = controller.getClass().getDeclaredField("mRepo");
            theaterRepoField.setAccessible(true);
            movieRepoField.setAccessible(true);
            theaterRepoField.set(controller, theaterRepo);
            movieRepoField.set(controller, movieRepo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
