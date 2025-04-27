package com.movieplan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movieplan.repository.BookedSeatsRepository;
import com.movieplan.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.movieplan.model.BookSeatsRequest;
import com.movieplan.model.BookedSeats;
import com.movieplan.model.Theater;

@RestController
@CrossOrigin
@RequestMapping("/bookedseats")
public class SeatsBookingController {

	private final BookedSeatsRepository bsRepo;
	private final TheaterRepository tRepo;

	@Autowired
	public SeatsBookingController(BookedSeatsRepository bsRepo, TheaterRepository tRepo) {
		this.bsRepo = bsRepo;
		this.tRepo = tRepo;
	}

	@PostMapping("/getbydatetimetheaterid/{theaterId}")
	public ResponseEntity<Map<String, Object>> getBookSeatsByDateTime(
			@PathVariable long theaterId,
			@RequestBody BookSeatsRequest request) {

		Theater theater = tRepo.getOne(theaterId);
		Date date = request.getDate();
		String time = request.getTime();
		List<BookedSeats> bookedSeats = bsRepo.getBookSeatsByDateTime(theater, date, time);

		Map<String, Object> map = new HashMap<>();
		map.put("response", bookedSeats);

		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@PostMapping("/postbydatetimetheaterid/{theaterId}")
	public ResponseEntity<Map<String, Object>> postBookSeatsByDateTime(
			@PathVariable long theaterId,
			@RequestBody BookSeatsRequest request) {

		Theater theater = tRepo.getOne(theaterId);
		Date date = request.getDate();
		String time = request.getTime();

		for (String seat : request.getSeats()) {
			BookedSeats bookedSeats = new BookedSeats();
			bookedSeats.setId(0);
			bookedSeats.setDate(date);
			bookedSeats.setSeat(seat);
			bookedSeats.setTime(time);
			bookedSeats.setTheater(theater);
			bsRepo.save(bookedSeats);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("text", "booked seats successfully");

		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
}
