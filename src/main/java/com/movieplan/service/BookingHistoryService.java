package com.movieplan.service;

import java.util.List;

import com.movieplan.dto.BookingHistoryDTO;

public interface BookingHistoryService {

	List<BookingHistoryDTO> getAllBookingHistory();

	BookingHistoryDTO createBookingHistory(BookingHistoryDTO bookingHistoryDTO);
}
