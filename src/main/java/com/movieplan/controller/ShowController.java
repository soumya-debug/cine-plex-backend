package com.movieplan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplan.model.Movie;
import com.movieplan.dto.MovieShowDTO;
import com.movieplan.service.MovieShowService;

@RestController
@RequestMapping("/movieshow")
@CrossOrigin(origins = "http://localhost:4200")
public class ShowController {

	private final MovieShowService movieShowService;

	public ShowController(MovieShowService movieShowService) {
		this.movieShowService = movieShowService;
	}

	@GetMapping("/{showId}")
	public ResponseEntity<MovieShowDTO> getShow(@PathVariable long showId) {
		MovieShowDTO show = movieShowService.getShowById(showId);
		if (show == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(show);
	}

	@GetMapping("/")
	public ResponseEntity<List<MovieShowDTO>> getAllShows() {
		return ResponseEntity.ok(movieShowService.getAllShows());
	}

	@GetMapping("/active/")
	public ResponseEntity<List<Movie>> getAllActiveMovies() {
		return ResponseEntity.ok(movieShowService.getAllActiveMovies());
	}
}
