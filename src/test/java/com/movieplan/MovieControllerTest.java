package com.movieplan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Map;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.MovieController;
import com.movieplan.model.Movie;
import com.movieplan.repository.movieRepository;

class MovieControllerTest {

    private MovieController movieController;
    private movieRepository mRepo;

    @BeforeEach
    void setUp() {
        mRepo = mock(movieRepository.class);
        movieController = new MovieController();
        injectMockRepository(movieController, mRepo);
    }

    @Test
    void testGetMovie() {
        long movieId = 1;
        Movie movie = new Movie();
        movie.setId(movieId);

        when(mRepo.findById(movieId)).thenReturn(Optional.of(movie));

        Optional<Movie> result = movieController.getMovie(movieId);

        assertTrue(result.isPresent());
        assertEquals(movieId, result.get().getId());
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(mRepo.findAll()).thenReturn(movies);

        List<Movie> result = movieController.getAllMovies();

        assertEquals(2, result.size());
    }

    @Test
    void testAdd() {
        Movie movieToAdd = new Movie();
        movieToAdd.setId(0);

        when(mRepo.save(movieToAdd)).thenReturn(movieToAdd);

        Movie result = movieController.add(movieToAdd);

        assertEquals(0, result.getId());
    }

    @Test
    void testDelete() {
        long movieId = 1;

        ResponseEntity<Map<String, Object>> expectedResponse = ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("text", "Deleted Movie ID: " + movieId));

        ResponseEntity<Map<String, Object>> response = movieController.delete(movieId);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }


    // Method to inject the mock repository using reflection
    private void injectMockRepository(MovieController controller, movieRepository repository) {
        try {
            Field field = controller.getClass().getDeclaredField("mRepo");
            field.setAccessible(true);
            field.set(controller, repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}