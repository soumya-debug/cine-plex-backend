package com.movieplan.service.impl;

import com.movieplan.model.PaymentModel;
import com.movieplan.repository.CheckoutRepository;
import com.movieplan.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final CheckoutRepository checkoutRepository;

    public PaymentServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public List<PaymentModel> getAllPayments() {
        return checkoutRepository.findAll();
    }

    @Override
    public PaymentModel processCheckout(PaymentModel paymentDTO) {
        PaymentModel paymentModel = new PaymentModel(
                paymentDTO.getCardHolderName(),
                paymentDTO.getCardNumber(),
                paymentDTO.getCvv(),
                paymentDTO.getExpiryDate()
        );

        return checkoutRepository.save(paymentModel);
    }
}

