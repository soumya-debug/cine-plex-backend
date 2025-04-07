package com.movieplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.model.BookingHistory;
import com.movieplan.model.PaymentModel;
import com.movieplan.model.Movie;
import com.movieplan.model.Theater;
import com.movieplan.repository.BookingHistoryRepository;
import com.movieplan.repository.CheckoutRepository;
import com.movieplan.repository.movieRepository;
import com.movieplan.repository.theaterRepository;
import com.movieplan.repository.userRepository;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

	private final BookingHistoryRepository bookingHistoryRepository;
	private final userRepository userRepository;
	private final movieRepository movieRepository; // Assuming you have a MovieRepository
	private final theaterRepository theaterRepository; // Assuming you have a TheaterRepository
	private final CheckoutRepository checkoutRepository; // Assuming you have a CheckoutRepository

	@Autowired
	public BookingHistoryServiceImpl(BookingHistoryRepository bookingHistoryRepository, userRepository userRepository,
			movieRepository movieRepository, theaterRepository theaterRepository,
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
		BookingHistory bookingHistory = convertToBookingHistory(bookingHistoryDTO);
		BookingHistory createdBookingHistory = bookingHistoryRepository.save(bookingHistory);
		return convertToBookingHistoryDTO(createdBookingHistory);
	}

	private BookingHistory convertToBookingHistory(BookingHistoryDTO dto) {
		BookingHistory bookingHistory = new BookingHistory();

		// Perform the conversion from DTO to entity fields
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

		// Convert other fields
		// ...

		return bookingHistory;
	}

	private BookingHistoryDTO convertToBookingHistoryDTO(BookingHistory bookingHistory) {
		BookingHistoryDTO dto = new BookingHistoryDTO();

		// Perform the conversion from entity to DTO fields
		dto.setUser(bookingHistory.getUser().getEmail());
		dto.setMovie(bookingHistory.getMovie().getName());
		dto.setTheater(bookingHistory.getTheater().getTheatreName());
		dto.setId(bookingHistory.getCheckout().getId()); // Set checkout ID
		// Convert other fields
		// ...

		return dto;
	}
}
