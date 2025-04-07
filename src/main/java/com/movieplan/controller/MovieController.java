// This is the controller class responsible for handling HTTP requests related to movies.
package com.movieplan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.movieplan.model.Movie;
import com.movieplan.repository.movieRepository;

// This annotation declares that this class is a RESTful controller.
@RestController
// This annotation enables cross-origin requests from different origins.
@CrossOrigin
// This annotation maps the base URL for all the requests handled by this controller.
@RequestMapping("/movies")
public class MovieController {

    // Autowire the movieRepository for database interaction
    @Autowired
    //This line declares and initializes a private field named mRepo of type movieRepository. The @Autowired annotation tells Spring to automatically inject an instance of the movieRepository interface into this field.
    private movieRepository mRepo ;

    // Retrieve a movie by its ID using a GET request
    @GetMapping("/{movieId}")       //The @PathVariable annotation indicates that the movieId parameter comes from the URL path.
    public Optional<Movie> getMovie(@PathVariable long movieId) {
        Optional<Movie> theMovie = mRepo.findById(movieId);
        
        // Throw an exception if the movie is not found
        if (!theMovie.isPresent()) {
            throw new RuntimeException("Movie not found for ID: " + movieId);
        }
        
        return theMovie;
    }

    // Retrieve all movies using a GET request
    @GetMapping("/")
    public List<Movie> getAllMovies() {
        List<Movie> theMovies = mRepo.findAll();
        return theMovies;
    }


    // Add a new movie using a POST request
    @PostMapping("/")
    public Movie add(@RequestBody Movie theMovie) {
        theMovie.setId(0); // Setting ID to 0 to indicate a new movie
        Movie theResMovie = mRepo.save(theMovie);
        return theResMovie;
    }

    // Update an existing movie by ID using a PUT request
    @PutMapping("/{movieId}")
    public Movie editMovie(@PathVariable long movieId, @RequestBody Movie theMovie) {
        theMovie.setId(movieId);
        Movie resMovie = mRepo.save(theMovie);
        return resMovie;
    }

    // Delete a movie by ID using a DELETE request
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long movieId) {
        mRepo.deleteById(movieId);
        
        // Create a response map with a message
        Map<String, Object> map = new HashMap<>();
        map.put("text", "Deleted Movie ID: " + movieId);
        
        // Return a response entity with OK status and the map as the body
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
