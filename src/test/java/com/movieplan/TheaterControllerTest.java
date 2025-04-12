package com.movieplan;

import com.movieplan.controller.TheaterController;
import com.movieplan.dto.TheaterDTO;
import com.movieplan.model.Theater;
import com.movieplan.service.TheaterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TheaterControllerTest {

    private TheaterService theaterService;
    private TheaterController controller;

    @BeforeEach
    void setUp() {
        theaterService = mock(TheaterService.class);
        controller = new TheaterController();
        injectService(controller, theaterService);
    }

    @Test
    void testGetTheaterById() {
        Theater theater = new Theater();
        theater.setId(1);
        when(theaterService.getTheaterById(1)).thenReturn(Optional.of(theater));

        ResponseEntity<Optional<Theater>> response = controller.getTheater(1);
        assertTrue(response.getBody().isPresent());
        assertEquals(1, response.getBody().get().getId());
    }

    @Test
    void testAddTheater() {
        TheaterDTO dto = new TheaterDTO();
        dto.setMovieId(1);
        dto.setTheatreName("Test");
        dto.setTheatreAddress("Address");

        when(theaterService.addTheater(dto)).thenReturn(Map.of("text", "Successfully added"));

        var response = controller.add(dto);
        assertEquals("Successfully added", response.getBody().get("text"));
    }

    // Utility method to inject mock service
    private void injectService(TheaterController controller, TheaterService service) {
        try {
            var field = TheaterController.class.getDeclaredField("theaterService");
            field.setAccessible(true);
            field.set(controller, service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
