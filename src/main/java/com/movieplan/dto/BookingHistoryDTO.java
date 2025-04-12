package com.movieplan.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookingHistoryDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_email")
	private String user;

	@Column(name = "movie_name")
	private String movie;

	@Column(name = "theater_name")
	private String theater;

	@Column(name = "cardholder_name")
	private String cardHolderName;

	@Column(name = "cardnumber")
	private String cardNumber;

	public BookingHistoryDTO() {
		super();
	}

	public BookingHistoryDTO(String userEmail, String movieName, String theaterName, String cardHolderName,
							 String cardNumber) {
		this.user = userEmail;
		this.movie = movieName;
		this.theater = theaterName;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
	}

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
