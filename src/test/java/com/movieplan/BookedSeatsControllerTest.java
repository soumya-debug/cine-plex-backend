package com.movieplan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;
import java.lang.reflect.Field;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.SeatsBookingController;
import com.movieplan.model.BookSeatsRequest;
import com.movieplan.model.BookedSeats;
import com.movieplan.model.Theater;
import com.movieplan.repository.BookedSeatsRepository;
import com.movieplan.repository.TheaterRepository;

class BookedSeatsControllerTest {

    private SeatsBookingController bookedSeatsController;
    private BookedSeatsRepository bsRepo;
    private TheaterRepository tRepo;

    @BeforeEach
    void setUp() {
        bsRepo = mock(BookedSeatsRepository.class);
        tRepo = mock(TheaterRepository.class);
        bookedSeatsController = new SeatsBookingController(bsRepo, tRepo);
        injectMockRepositories(bookedSeatsController, bsRepo, tRepo);
    }

    @Test
    void testPostBookSeatsByDateTime() {
        long theaterId = 1;
        BookSeatsRequest request = new BookSeatsRequest();
        Theater theater = new Theater();
        Date date = new Date();
        String time = "10:00 AM";
        request.setDate(date);
        request.setTime(time);
        request.setSeats(Arrays.asList("A1", "A2"));

        when(tRepo.getOne(theaterId)).thenReturn(theater);

        ResponseEntity<Map<String, Object>> response = bookedSeatsController.postBookSeatsByDateTime(theaterId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("booked seats successfully", response.getBody().get("text"));

        // Verify that save method was called for each seat
        verify(bsRepo, times(request.getSeats().size())).save(any(BookedSeats.class));
    }

    // Method to inject the mock repositories using reflection
    private void injectMockRepositories(SeatsBookingController controller, BookedSeatsRepository bsRepo,
            TheaterRepository tRepo) {
        try {
            Field bsRepoField = controller.getClass().getDeclaredField("bsRepo");
            Field tRepoField = controller.getClass().getDeclaredField("tRepo");
            bsRepoField.setAccessible(true);
            tRepoField.setAccessible(true);
            bsRepoField.set(controller, bsRepo);
            tRepoField.set(controller, tRepo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
