package com.movieplan.service.impl;

import java.util.List;

import com.movieplan.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.model.PaymentModel;
import com.movieplan.model.Movie;
import com.movieplan.model.Theater;
import com.movieplan.repository.BookingHistoryRepository;
import com.movieplan.repository.CheckoutRepository;
import com.movieplan.repository.MovieRepository;
import com.movieplan.repository.theaterRepository;
import com.movieplan.repository.UserRepository;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

	private final BookingHistoryRepository bookingHistoryRepository;
	private final UserRepository userRepository;
	private final MovieRepository movieRepository;
	private final theaterRepository theaterRepository;
	private final CheckoutRepository checkoutRepository;

	@Autowired
	public BookingHistoryServiceImpl(BookingHistoryRepository bookingHistoryRepository, UserRepository userRepository,
									 MovieRepository movieRepository, theaterRepository theaterRepository,
									 CheckoutRepository checkoutRepository) {
		this.bookingHistoryRepository = bookingHistoryRepository;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
		this.theaterRepository = theaterRepository;
		this.checkoutRepository = checkoutRepository;
	}

	@Override
	public List<BookingHistoryDTO> getAllBookingHistory() {
		return bookingHistoryRepository.getAllBookingHistory();
	}

	@Override
	public BookingHistoryDTO createBookingHistory(BookingHistoryDTO bookingHistoryDTO) {
		com.movieplan.model.BookingHistory bookingHistory = convertToBookingHistory(bookingHistoryDTO);
		com.movieplan.model.BookingHistory createdBookingHistory = bookingHistoryRepository.save(bookingHistory);
		return convertToBookingHistoryDTO(createdBookingHistory);
	}

	private com.movieplan.model.BookingHistory convertToBookingHistory(BookingHistoryDTO dto) {
		com.movieplan.model.BookingHistory bookingHistory = new com.movieplan.model.BookingHistory();

		bookingHistory.setUser(userRepository.findByEmail(dto.getUser()));
		Movie movie = movieRepository.findByName(dto.getMovie());

		if (movie == null) {
			throw new IllegalArgumentException("Movie not found with name: " + dto.getMovie());
		}

		bookingHistory.setMovie(movie);

		Theater theater = theaterRepository.findByTheatreName(dto.getTheater());

		if (theater == null) {
			throw new IllegalArgumentException("Theater not found with name: " + dto.getTheater());
		}

		bookingHistory.setTheater(theater);

		PaymentModel checkout = checkoutRepository
				.findByCardHolderNameAndCardNumber(dto.getCardHolderName(), dto.getCardNumber())
				.orElseThrow(() -> new IllegalArgumentException("Checkout not found with card holder name: "
						+ dto.getCardHolderName() + " and card number: " + dto.getCardNumber()));
		bookingHistory.setCheckout(checkout);

		return bookingHistory;
	}

	private BookingHistoryDTO convertToBookingHistoryDTO(com.movieplan.model.BookingHistory bookingHistory) {
		BookingHistoryDTO dto = new BookingHistoryDTO();

		dto.setUser(bookingHistory.getUser().getEmail());
		dto.setMovie(bookingHistory.getMovie().getName());
		dto.setTheater(bookingHistory.getTheater().getTheatreName());
		dto.setId(bookingHistory.getCheckout().getId()); // Set checkout ID

		return dto;

	}
}
