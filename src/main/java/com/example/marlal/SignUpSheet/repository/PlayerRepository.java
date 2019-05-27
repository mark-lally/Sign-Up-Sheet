package com.example.marlal.SignUpSheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.marlal.SignUpSheet.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	
	public long countByAvailability(Boolean trueOrFalse);
}
