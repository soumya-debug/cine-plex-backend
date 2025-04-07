package com.movieplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.movieplan.controller.PaymentController;
import com.movieplan.model.PaymentModel;
import com.movieplan.repository.CheckoutRepository;

class CheckoutControllerTest {

	private PaymentController checkoutController;

	@Mock
	private CheckoutRepository checkoutRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		checkoutController = new PaymentController(checkoutRepository);
	}

	@Test
	void testShowCheckoutForm() {
	    List<PaymentModel> mockCheckoutDataList = new ArrayList<>(); // Mock your checkout data list

	    when(checkoutRepository.findAll()).thenReturn(mockCheckoutDataList);

	    ResponseEntity<List<PaymentModel>> responseEntity = checkoutController.showCheckoutForm();

	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(mockCheckoutDataList, responseEntity.getBody());
	}


	@Test
	void testProcessCheckout() {
		PaymentModel checkoutData = new PaymentModel();
		when(checkoutRepository.save(checkoutData)).thenReturn(checkoutData);

		PaymentModel result = checkoutController.processCheckout(checkoutData);

		assertEquals(checkoutData, result);
		verify(checkoutRepository).save(checkoutData);
	}
}