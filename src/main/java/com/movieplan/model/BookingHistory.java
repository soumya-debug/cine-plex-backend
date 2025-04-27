package com.movieplan.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_history")
public class BookingHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "theater_id")
	private Theater theater;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "checkout_id")
	private PaymentModel checkout;

	// No-arg constructor
	public BookingHistory() {
		// No-argument constructor required by JPA
	}

	// All-args constructor
	public BookingHistory(Long id, User user, Movie movie, Theater theater, PaymentModel checkout) {
		this.id = id;
		this.user = user;
		this.movie = movie;
		this.theater = theater;
		this.checkout = checkout;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public PaymentModel getCheckout() {
		return checkout;
	}

	public void setCheckout(PaymentModel checkout) {
		this.checkout = checkout;
	}
}
