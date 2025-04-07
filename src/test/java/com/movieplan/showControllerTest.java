package com.movieplan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.showController;
import com.movieplan.model.MovieShow;
import com.movieplan.repository.showRepository;

class showControllerTest {

    private showController showController;
    private showRepository sRepo;

    @BeforeEach
    void setUp() {
        sRepo = mock(showRepository.class);
        showController = new showController(sRepo);
    }

    @Test
    void testGetShowById() {
        long showId = 1;
        MovieShow show = new MovieShow();
        // Set properties of the show as needed using constructor or setter methods
        // For example:
        // show.setName("Example Show");

        when(sRepo.findById(showId)).thenReturn(Optional.of(show));

        ResponseEntity<MovieShow> response = showController.getShow(showId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(show, response.getBody());
    }

    @Test
    void testGetShowByIdNotFound() {
        long showId = 1;

        when(sRepo.findById(showId)).thenReturn(Optional.empty());

        ResponseEntity<MovieShow> response = showController.getShow(showId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllShows() {
        List<MovieShow> shows = new ArrayList<>();
        shows.add(new MovieShow());
        shows.add(new MovieShow());

        when(sRepo.findAll()).thenReturn(shows);

        List<MovieShow> result = showController.getAllShows();

        assertEquals(2, result.size());
    }
}