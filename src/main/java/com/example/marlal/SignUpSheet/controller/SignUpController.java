package com.example.marlal.SignUpSheet.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.marlal.SignUpSheet.entity.Player;
import com.example.marlal.SignUpSheet.repository.PlayerRepository;

@Controller
public class SignUpController {
	
	@Autowired
	PlayerRepository playerRepository;
	
	List<Player> thePlayers;
	
	public long getCount() {
		return playerRepository.count();
	}
	
	@GetMapping("/")
	public String showPlayers(Model theModel) {
		thePlayers = playerRepository.findAll();
		long numPlayers = getCount();
		long numAvail = playerRepository.countByAvailability(true);
		Collections.sort(thePlayers, new Comparator<Player>() {
			@Override
			public int compare(Player player1, Player player2) {
				return -Boolean.compare(player1.getAvailability(), player2.getAvailability());
			}
		});
		theModel.addAttribute("players", thePlayers);
		theModel.addAttribute("playerCount", numPlayers);
		theModel.addAttribute("numAvail", numAvail);
		
		return "index";
	}
	
	@GetMapping("/registerForm")
	public String registerForm(Model theModel) {
		
		// Create a blank Player object to bind form data to
		Player thePlayer = new Player();
		theModel.addAttribute("player", thePlayer);
		
		return "registerForm";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("playerId") int theId) {
		playerRepository.deleteById(theId);
		
		return "redirect:";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("player") Player thePlayer) {
		
		// save the player
		playerRepository.save(thePlayer);
		
		return "redirect:";
	}
}
