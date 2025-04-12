package com.movieplan.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplan.model.Theater;
import com.movieplan.dto.TheaterDTO;
import com.movieplan.service.TheaterService;

@RestController
@CrossOrigin
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

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
