package com.movieplan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingHistoryDTO {
    private Long id;
    private String user; // Email
    private String movie;
    private String theater;
    private String cardHolderName;
    private String cardNumber;

    public BookingHistoryDTO(String user, String movie, String theater, String cardHolderName, String cardNumber) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
    }
}
