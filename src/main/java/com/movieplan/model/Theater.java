package com.movieplan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String theatreName;

	@Column
	private String theatreAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")  // Ensure this matches the column name in your database
	@JsonIgnoreProperties({"theater"})
	private Movie movie;

	@OneToMany(mappedBy = "theater")
	private List<BookedSeats> seats;

	public Theater() {}

	// Getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreAddress() {
		return theatreAddress;
	}
	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
