package com.movieplan.service;

import com.movieplan.model.PaymentModel;

import java.util.List;

public interface PaymentService {

    List<PaymentModel> getAllPayments();

    PaymentModel processCheckout(PaymentModel paymentDTO);
}

