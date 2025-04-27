package com.movieplan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.movieplan.controller.ShowController;
import com.movieplan.dto.MovieShowDTO;
import com.movieplan.model.Movie;
import com.movieplan.service.MovieShowService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ShowControllerTest {

    private ShowController showController;
    private MovieShowService movieShowService;

    @BeforeEach
    void setUp() {
        movieShowService = mock(MovieShowService.class);
        showController = new ShowController(movieShowService);
    }

    @Test
    void testGetShowById_Found() {
        long showId = 1L;
        MovieShowDTO mockDTO = new MovieShowDTO(showId, "ACTIVE");

        when(movieShowService.getShowById(showId)).thenReturn(mockDTO);

        ResponseEntity<MovieShowDTO> response = showController.getShow(showId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDTO, response.getBody());
    }

    @Test
    void testGetShowById_NotFound() {
        long showId = 1L;

        when(movieShowService.getShowById(showId)).thenReturn(null);

        ResponseEntity<MovieShowDTO> response = showController.getShow(showId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllShows() {
        List<MovieShowDTO> mockList = Arrays.asList(
                new MovieShowDTO(1L, "ACTIVE"),
                new MovieShowDTO(2L, "INACTIVE")
        );

        when(movieShowService.getAllShows()).thenReturn(mockList);

        ResponseEntity<List<MovieShowDTO>> response = showController.getAllShows();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetAllActiveMovies() {
        List<Movie> movies = Collections.singletonList(new Movie());

        when(movieShowService.getAllActiveMovies()).thenReturn(movies);

        ResponseEntity<List<Movie>> response = showController.getAllActiveMovies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}
