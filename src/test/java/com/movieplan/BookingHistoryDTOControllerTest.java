package com.movieplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.BookingHistoryController;
import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.service.BookingHistoryService;

public class BookingHistoryDTOControllerTest {

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
		List<BookingHistoryDTO> bookingHistoryDTOList = new ArrayList<>();
		// Add test data to the bookingHistoryList

		when(bookingHistoryService.getAllBookingHistory()).thenReturn(bookingHistoryDTOList);

		List<BookingHistoryDTO> result = bookingHistoryController.getAllBookingHistory();

		assertEquals(bookingHistoryDTOList, result);
	}

	@Test
	public void testCreateBookingHistory() {
		BookingHistoryDTO bookingHistoryDTO = new BookingHistoryDTO();
		// Set test data in bookingHistoryDTO

		BookingHistoryDTO createdBookingHistoryDTO = new BookingHistoryDTO();
		// Set test data in createdBookingHistory

		when(bookingHistoryService.createBookingHistory(bookingHistoryDTO)).thenReturn(createdBookingHistoryDTO);

		ResponseEntity<String> response = bookingHistoryController.createBookingHistory(bookingHistoryDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Booking history created successfully", response.getBody());
	}

	@Test
	public void testCreateBookingHistoryFailure() {
		BookingHistoryDTO bookingHistoryDTO = new BookingHistoryDTO();
		// Set test data in bookingHistoryDTO

		when(bookingHistoryService.createBookingHistory(bookingHistoryDTO)).thenReturn(null);

		ResponseEntity<String> response = bookingHistoryController.createBookingHistory(bookingHistoryDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Failed to create booking history", response.getBody());
	}
}