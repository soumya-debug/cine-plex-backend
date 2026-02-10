package com.movieplan.controller;

import java.util.Map;

import com.movieplan.service.SeatsBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.movieplan.model.BookSeatsRequest;

@RestController
@CrossOrigin
@RequestMapping("/bookedseats")
public class SeatsBookingController {

	private final SeatsBookingService seatsBookingService;

	@Autowired
	public SeatsBookingController(SeatsBookingService seatsBookingService) {
		this.seatsBookingService = seatsBookingService;
	}

	@PostMapping("/getbydatetimetheaterid/{theaterId}")
	public ResponseEntity<Map<String, Object>> getBookSeatsByDateTime(
			@PathVariable long theaterId,
			@RequestBody BookSeatsRequest request) {
		Map<String, Object> map = seatsBookingService.getBookSeatsByDateTime(theaterId, request);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@PostMapping("/postbydatetimetheaterid/{theaterId}")
	public ResponseEntity<Map<String, Object>> postBookSeatsByDateTime(
			@PathVariable long theaterId,
			@RequestBody BookSeatsRequest request) {
		Map<String, Object> map = seatsBookingService.postBookSeatsByDateTime(theaterId, request);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
}
