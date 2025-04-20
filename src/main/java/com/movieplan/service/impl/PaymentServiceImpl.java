package com.movieplan.service.impl;

import com.movieplan.model.PaymentModel;
import com.movieplan.repository.PaymentRepository;
import com.movieplan.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentModel processCheckout(PaymentModel paymentDTO) {
        PaymentModel paymentModel = new PaymentModel(
                paymentDTO.getCardHolderName(),
                paymentDTO.getCardNumber(),
                paymentDTO.getCvv(),
                paymentDTO.getExpiryDate()
        );

        return paymentRepository.save(paymentModel);
    }
}

