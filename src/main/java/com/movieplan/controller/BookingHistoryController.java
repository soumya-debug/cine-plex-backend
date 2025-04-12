
package com.movieplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.service.BookingHistoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/booking-history")
public class BookingHistoryController {

	private final BookingHistoryService bookingHistoryService;

	@Autowired
	public BookingHistoryController(BookingHistoryService bookingHistoryService) {
		this.bookingHistoryService = bookingHistoryService;
	}

	@GetMapping("/")
	public List<BookingHistoryDTO> getAllBookingHistory() {
		return bookingHistoryService.getAllBookingHistory();
	}

	@PostMapping("/create")
	public ResponseEntity<String> createBookingHistory(@RequestBody BookingHistoryDTO bookingHistoryDTO) {
		BookingHistoryDTO createdBookingHistoryDTO = bookingHistoryService.createBookingHistory(bookingHistoryDTO);

		if (createdBookingHistoryDTO != null) {
			return new ResponseEntity<>("Booking history created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed to create booking history", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
