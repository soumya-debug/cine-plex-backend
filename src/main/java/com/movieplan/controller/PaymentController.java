package com.movieplan.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;


import com.movieplan.model.PaymentModel;
import com.movieplan.repository.CheckoutRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/checkout")
public class PaymentController {

	private final CheckoutRepository checkoutRepository;

	public PaymentController(CheckoutRepository checkoutRepository) {
		this.checkoutRepository = checkoutRepository;
	}

	@GetMapping("/showForm")
	public ResponseEntity<List<PaymentModel>> showCheckoutForm() {
	    List<PaymentModel> checkoutDataList = checkoutRepository.findAll();
	    
	    return ResponseEntity.ok(checkoutDataList);
	}


	@PostMapping("/processCheckout")
	public PaymentModel processCheckout(@RequestBody PaymentModel checkoutData) {

		PaymentModel checkout = checkoutRepository.save(checkoutData);

		return checkout;

	}
}