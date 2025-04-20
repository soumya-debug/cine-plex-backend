package com.movieplan;

import com.movieplan.controller.BookingHistoryController;
import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.service.BookingHistoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

public class BookingHistoryControllerTest {

	@InjectMocks
	private BookingHistoryController bookingHistoryController;

	@Mock
	private BookingHistoryService bookingHistoryService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllBookingHistory() {
		List<BookingHistoryDTO> mockList = new ArrayList<>();
		when(bookingHistoryService.getAllBookingHistory()).thenReturn(mockList);

		ResponseEntity<List<BookingHistoryDTO>> response = bookingHistoryController.getAllBookingHistory();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockList, response.getBody());
	}

	@Test
	public void testCreateBookingHistorySuccess() {
		BookingHistoryDTO dto = new BookingHistoryDTO();
		ResponseEntity<String> response = bookingHistoryController.createBookingHistory(dto);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Booking history created successfully", response.getBody());
	}

	@Test
	public void testCreateBookingHistoryBadRequest() {
		BookingHistoryDTO dto = new BookingHistoryDTO();

		doThrow(new IllegalArgumentException("Invalid booking data")).when(bookingHistoryService).createBookingHistory(dto);

		ResponseEntity<String> response = bookingHistoryController.createBookingHistory(dto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid booking data", response.getBody());
	}

	@Test
	public void testCreateBookingHistoryServerError() {
		BookingHistoryDTO dto = new BookingHistoryDTO();

		doThrow(new RuntimeException("Unexpected error")).when(bookingHistoryService).createBookingHistory(dto);

		ResponseEntity<String> response = bookingHistoryController.createBookingHistory(dto);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Failed to create booking history", response.getBody());
	}
}
