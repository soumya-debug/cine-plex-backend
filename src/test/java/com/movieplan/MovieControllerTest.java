package com.movieplan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.movieplan.controller.MovieController;
import com.movieplan.model.Movie;
import com.movieplan.service.MovieService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

class MovieControllerTest {

    private MovieController movieController;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = mock(MovieService.class);
        movieController = new MovieController();
        injectMockService(movieController, movieService);
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = List.of(new Movie(), new Movie());

        when(movieService.getAllMovies()).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void testGetMovieById() {
        long movieId = 1;
        Movie movie = new Movie();
        movie.setId(movieId);

        when(movieService.getMovieById(movieId)).thenReturn(movie);

        ResponseEntity<Movie> response = movieController.getMovieById(movieId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(movieId, response.getBody().getId());
    }

    @Test
    void testAddMovie() {
        Movie movieToAdd = new Movie();
        movieToAdd.setName("New Movie");

        when(movieService.addMovie(movieToAdd)).thenReturn(movieToAdd);

        ResponseEntity<Movie> response = movieController.addMovie(movieToAdd);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New Movie", response.getBody().getName());
    }

    @Test
    void testUpdateMovie() {
        long movieId = 1;
        Movie updatedMovie = new Movie();
        updatedMovie.setId(movieId);
        updatedMovie.setName("Updated");

        when(movieService.updateMovie(movieId, updatedMovie)).thenReturn(updatedMovie);

        ResponseEntity<Movie> response = movieController.updateMovie(movieId, updatedMovie);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody().getName());
    }

    @Test
    void testDeleteMovie() {
        long movieId = 1;

        // Since deleteMovie returns void, just verify it's called
        doNothing().when(movieService).deleteMovie(movieId);

        ResponseEntity<Void> response = movieController.deleteMovie(movieId);

        verify(movieService).deleteMovie(movieId);
        assertEquals(204, response.getStatusCodeValue());
    }

    // Inject mock MovieService via reflection
    private void injectMockService(MovieController controller, MovieService service) {
        try {
            var field = controller.getClass().getDeclaredField("movieService");
            field.setAccessible(true);
            field.set(controller, service);
        } catch (Exception e) {
            fail("Failed to inject mock MovieService", e);
        }
    }
}
