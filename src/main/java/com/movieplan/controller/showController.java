package com.movieplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplan.model.Movie;
import com.movieplan.model.MovieShow;
import com.movieplan.repository.movieRepository;
import com.movieplan.repository.showRepository;

@RestController
@RequestMapping("/movieshow")
@CrossOrigin(origins = "http://localhost:4200")
public class showController {

	private final showRepository sRepo;

	@Autowired
	private movieRepository mRepo;

	@Autowired
	public showController(showRepository sRepo) {
		this.sRepo = sRepo;
	}

	@GetMapping("/{showId}")
	public ResponseEntity<MovieShow> getShow(@PathVariable long showId) {
		MovieShow show = sRepo.findById(showId).orElse(null);
		if (show == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(show);
	}

	@GetMapping("/")
	public List<MovieShow> getAllShows() {
		return sRepo.findAll();
	}

	@GetMapping("/active/")
	public List<Movie> getAllActiveMovies() {

		List<Movie> theMovies = mRepo.getMoviesByTheater();

		return theMovies;
	}
}