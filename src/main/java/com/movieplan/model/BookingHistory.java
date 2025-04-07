package com.movieplan.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

	// Constructors, getters, setters...

	public BookingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	// Constructors, getters, and setters...
}
