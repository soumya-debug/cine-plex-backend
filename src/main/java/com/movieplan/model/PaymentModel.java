package com.movieplan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checkout")
public class PaymentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "cvv")
	private String cvv;

	@Column(name = "expiry_date")
	private String expiryDate;

	public PaymentModel() {
		// Default constructor
	}

	public PaymentModel(String cardHolderName, String cardNumber, String cvv, String expiryDate) {
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
