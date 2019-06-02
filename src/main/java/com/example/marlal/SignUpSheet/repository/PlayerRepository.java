package com.example.marlal.SignUpSheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.marlal.SignUpSheet.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {	
	
	/* 
	 * Uses the Jpa criteria api syntax - query is based off method name.
	 * For reference: 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 */ 
	public long countByAvailability(Boolean trueOrFalse);
}
