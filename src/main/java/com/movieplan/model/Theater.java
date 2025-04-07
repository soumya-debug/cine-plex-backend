package com.movieplan.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column
	private String theatreName;

	@Column
	private String theatreAddress;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "movie")
	private Movie movie;

	@OneToMany(mappedBy = "theater")
	private List<BookedSeats> seats;

	public Theater() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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

}
