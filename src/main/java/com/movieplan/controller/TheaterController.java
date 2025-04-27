package com.movieplan.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movieplan.model.Theater;
import com.movieplan.dto.TheaterDTO;
import com.movieplan.service.TheaterService;

@RestController
@CrossOrigin
@RequestMapping("/theater")
public class TheaterController {

	private final TheaterService theaterService;

	@Autowired
	public TheaterController(TheaterService theaterService) {
		this.theaterService = theaterService;
	}

	@GetMapping("/{theaterId}")
	public ResponseEntity<Optional<Theater>> getTheater(@PathVariable long theaterId) {
		return ResponseEntity.ok(theaterService.getTheaterById(theaterId));
	}

	@GetMapping("/")
	public ResponseEntity<List<Theater>> getAllTheaters() {
		return ResponseEntity.ok(theaterService.getAllTheaters());
	}

	@GetMapping("/bymovie/{movieId}")
	public ResponseEntity<Map<String, Object>> getAllTheatersByMovieId(@PathVariable long movieId) {
		List<Theater> theaters = theaterService.getTheatersByMovieId(movieId);
		return ResponseEntity.ok(Map.of("text", theaters));
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> add(@RequestBody TheaterDTO dto) {
		// Create a Theater entity from DTO and pass movieId to service layer
		return ResponseEntity.ok(theaterService.addTheater(dto));
	}

	@PutMapping("/{theaterId}")
	public ResponseEntity<Map<String, Object>> editMovie(@PathVariable long theaterId, @RequestBody TheaterDTO dto) {
		return ResponseEntity.ok(theaterService.updateTheater(theaterId, dto));
	}

	@DeleteMapping("/{theaterId}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable long theaterId) {
		return ResponseEntity.ok(theaterService.deleteTheater(theaterId));
	}
}
