package com.movieplan.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkout")
@Data
@NoArgsConstructor
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

    public PaymentModel(String cardHolderName, String cardNumber, String cvv, String expiryDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
}
