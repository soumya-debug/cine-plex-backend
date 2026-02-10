package com.movieplan.service.impl;

import com.movieplan.model.BookSeatsRequest;
import com.movieplan.model.BookedSeats;
import com.movieplan.model.Theater;
import com.movieplan.repository.BookedSeatsRepository;
import com.movieplan.repository.TheaterRepository;
import com.movieplan.service.SeatsBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatsBookingServiceImpl implements SeatsBookingService {

    private final BookedSeatsRepository bsRepo;
    private final TheaterRepository tRepo;

    @Autowired
    public SeatsBookingServiceImpl(BookedSeatsRepository bsRepo, TheaterRepository tRepo) {
        this.bsRepo = bsRepo;
        this.tRepo = tRepo;
    }

    @Override
    public Map<String, Object> getBookSeatsByDateTime(long theaterId, BookSeatsRequest request) {
        Theater theater = tRepo.findById(theaterId).orElse(null);
        Date date = request.getDate();
        String time = request.getTime();
        List<BookedSeats> bookedSeats = bsRepo.getBookSeatsByDateTime(theater, date, time);
        Map<String, Object> map = new HashMap<>();
        map.put("response", bookedSeats);
        return map;
    }

    @Override
    public Map<String, Object> postBookSeatsByDateTime(long theaterId, BookSeatsRequest request) {
        Theater theater = tRepo.findById(theaterId).orElse(null);
        Date date = request.getDate();
        String time = request.getTime();
        for (String seat : request.getSeats()) {
            BookedSeats bookedSeats = new BookedSeats();
            bookedSeats.setId(0);
            bookedSeats.setDate(date);
            bookedSeats.setSeat(seat);
            bookedSeats.setTime(time);
            bookedSeats.setTheater(theater);
            bsRepo.save(bookedSeats);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("text", "booked seats successfully");
        return map;
    }
}
