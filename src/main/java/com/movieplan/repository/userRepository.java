package com.movieplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplan.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}