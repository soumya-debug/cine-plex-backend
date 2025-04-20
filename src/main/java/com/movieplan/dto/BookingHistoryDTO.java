package com.movieplan.dto;

public class BookingHistoryDTO {

	private Long id;
	private String user; // Email
	private String movie;
	private String theater;
	private String cardHolderName;
	private String cardNumber;

	public BookingHistoryDTO() {
	}

	public BookingHistoryDTO(String user, String movie, String theater, String cardHolderName, String cardNumber) {
		this.user = user;
		this.movie = movie;
		this.theater = theater;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
	}

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
