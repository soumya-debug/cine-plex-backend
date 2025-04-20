package com.movieplan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplan.model.PaymentModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
	Optional<PaymentModel> findByCardHolderNameAndCardNumber(String cardHolderName, String cardNumber);

}
