package com.movieplan.service;

import com.movieplan.model.BookSeatsRequest;

import java.util.Map;

public interface SeatsBookingService {

    Map<String, Object> getBookSeatsByDateTime(long theaterId, BookSeatsRequest request);

    Map<String, Object> postBookSeatsByDateTime(long theaterId, BookSeatsRequest request);
}
