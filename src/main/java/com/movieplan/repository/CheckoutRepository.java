package com.movieplan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplan.model.PaymentModel;

public interface CheckoutRepository extends JpaRepository<PaymentModel, Long> {
	// You can add custom query methods here if needed
	Optional<PaymentModel> findByCardHolderNameAndCardNumber(String cardHolderName, String cardNumber);

}
