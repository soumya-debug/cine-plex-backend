package com.movieplan.controller;

import java.util.List;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.service.BookingHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<List<BookingHistoryDTO>> getAllBookingHistory() {
		return new ResponseEntity<>(bookingHistoryService.getAllBookingHistory(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createBookingHistory(@RequestBody BookingHistoryDTO bookingHistoryDTO) {
		try {
			bookingHistoryService.createBookingHistory(bookingHistoryDTO);
			return new ResponseEntity<>("Booking history created successfully", HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to create booking history", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
