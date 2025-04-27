package com.movieplan;

import com.movieplan.controller.PaymentController;
import com.movieplan.model.PaymentModel;
import com.movieplan.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

	@InjectMocks
	private PaymentController paymentController;

	@Mock
	private PaymentService paymentService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowCheckoutForm() {
		List<PaymentModel> mockList = new ArrayList<>();
		when(paymentService.getAllPayments()).thenReturn(mockList);

		ResponseEntity<List<PaymentModel>> response = paymentController.showCheckoutForm();

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(mockList, response.getBody());
	}

	@Test
	public void testProcessCheckout() {
		PaymentModel mockPayment = new PaymentModel();
		when(paymentService.processCheckout(mockPayment)).thenReturn(mockPayment);

		ResponseEntity<PaymentModel> response = paymentController.processCheckout(mockPayment);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(mockPayment, response.getBody());
	}
}
