package com.movieplan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.SeatsBookingController;
import com.movieplan.model.BookSeatsRequest;
import com.movieplan.service.SeatsBookingService;

class BookedSeatsControllerTest {

    private SeatsBookingController bookedSeatsController;
    private SeatsBookingService seatsBookingService;

    @BeforeEach
    void setUp() {
        seatsBookingService = mock(SeatsBookingService.class);
        bookedSeatsController = new SeatsBookingController(seatsBookingService);
    }

    @Test
    void testPostBookSeatsByDateTime() {
        long theaterId = 1;
        BookSeatsRequest request = new BookSeatsRequest();
        Date date = new Date();
        String time = "10:00 AM";
        request.setDate(date);
        request.setTime(time);
        request.setSeats(Arrays.asList("A1", "A2"));

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("text", "booked seats successfully");
        when(seatsBookingService.postBookSeatsByDateTime(theaterId, request)).thenReturn(expectedResponse);

        ResponseEntity<Map<String, Object>> response = bookedSeatsController.postBookSeatsByDateTime(theaterId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("booked seats successfully", response.getBody().get("text"));
        verify(seatsBookingService, times(1)).postBookSeatsByDateTime(theaterId, request);
    }
}
