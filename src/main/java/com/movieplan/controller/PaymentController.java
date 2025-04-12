package com.movieplan.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.movieplan.model.PaymentModel;
import com.movieplan.service.PaymentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/checkout")
public class PaymentController {

	private final PaymentService paymentService;

	// Constructor injection for PaymentService
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	// Get all payments
	@GetMapping("/showForm")
	public ResponseEntity<List<PaymentModel>> showCheckoutForm() {
		List<PaymentModel> checkoutDataList = paymentService.getAllPayments();
		return ResponseEntity.ok(checkoutDataList);
	}

	// Process checkout payment
	@PostMapping("/processCheckout")
	public ResponseEntity<PaymentModel> processCheckout(@RequestBody PaymentModel paymentDTO) {
		PaymentModel checkout = paymentService.processCheckout(paymentDTO);
		return ResponseEntity.ok(checkout);
	}
}
