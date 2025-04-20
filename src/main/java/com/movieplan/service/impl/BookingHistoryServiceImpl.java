package com.movieplan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.model.*;
import com.movieplan.repository.*;
import com.movieplan.service.BookingHistoryService;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

	private final BookingHistoryRepository bookingHistoryRepository;
	private final UserRepository userRepository;
	private final MovieRepository movieRepository;
	private final theaterRepository theaterRepository;
	private final PaymentRepository paymentRepository;

	@Autowired
	public BookingHistoryServiceImpl(
            BookingHistoryRepository bookingHistoryRepository,
            UserRepository userRepository,
            MovieRepository movieRepository,
            theaterRepository theaterRepository,
            PaymentRepository paymentRepository
    ) {
		this.bookingHistoryRepository = bookingHistoryRepository;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
		this.theaterRepository = theaterRepository;
        this.paymentRepository = paymentRepository;
	}

	@Override
	public List<BookingHistoryDTO> getAllBookingHistory() {
		return bookingHistoryRepository.getAllBookingHistory();
	}

	@Override
	public BookingHistoryDTO createBookingHistory(BookingHistoryDTO dto) {
		if (dto.getUser() == null || dto.getUser().isEmpty()) {
			throw new IllegalArgumentException("User email must not be null or empty");
		}

		BookingHistory bookingHistory = convertToBookingHistory(dto);
		BookingHistory saved = bookingHistoryRepository.save(bookingHistory);
		return convertToBookingHistoryDTO(saved);
	}

	private BookingHistory convertToBookingHistory(BookingHistoryDTO dto) {
		BookingHistory history = new BookingHistory();

		User user = Optional.ofNullable(userRepository.findByEmail(dto.getUser()))
				.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + dto.getUser()));
		history.setUser(user);

		Movie movie = Optional.ofNullable(movieRepository.findByName(dto.getMovie()))
				.orElseThrow(() -> new IllegalArgumentException("Movie not found with name: " + dto.getMovie()));
		history.setMovie(movie);

		Theater theater = Optional.ofNullable(theaterRepository.findByTheatreName(dto.getTheater()))
				.orElseThrow(() -> new IllegalArgumentException("Theater not found with name: " + dto.getTheater()));
		history.setTheater(theater);

		PaymentModel checkout = paymentRepository
				.findByCardHolderNameAndCardNumber(dto.getCardHolderName(), dto.getCardNumber())
				.orElseThrow(() -> new IllegalArgumentException("Payment not found with given card details."));
		history.setCheckout(checkout);

		return history;
	}

	private BookingHistoryDTO convertToBookingHistoryDTO(BookingHistory history) {
		BookingHistoryDTO dto = new BookingHistoryDTO();

		dto.setId(history.getId());
		dto.setUser(Optional.ofNullable(history.getUser()).map(User::getEmail).orElse("Unknown"));
		dto.setMovie(Optional.ofNullable(history.getMovie()).map(Movie::getName).orElse("Unknown"));
		dto.setTheater(Optional.ofNullable(history.getTheater()).map(Theater::getTheatreName).orElse("Unknown"));
		dto.setCardHolderName(history.getCheckout() != null ? history.getCheckout().getCardHolderName() : "Unknown");
		dto.setCardNumber(history.getCheckout() != null ? history.getCheckout().getCardNumber() : "****");

		return dto;
	}
}
