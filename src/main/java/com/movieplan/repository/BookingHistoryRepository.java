package com.movieplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.movieplan.dto.BookingHistoryDTO;
import com.movieplan.model.BookingHistory;

@Repository
@EnableJpaRepositories
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {

	@Query("SELECT NEW com.movieplan.dto.BookingHistoryDTO(u.email, m.name, t.theatreName, ch.cardHolderName, ch.cardNumber) "
			+ "FROM BookingHistory b " + "JOIN b.user u " + "JOIN b.movie m " + "JOIN b.theater t "
			+ "JOIN b.checkout ch")
	List<BookingHistoryDTO> getAllBookingHistory();

}
