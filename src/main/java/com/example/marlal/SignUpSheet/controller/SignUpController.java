package com.example.marlal.SignUpSheet.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
	
	// Get a count of all registered players
	public long getCount() {
		
		return playerRepository.count();
	}
	
	@GetMapping("/")
	public String showPlayers(Model theModel) {
		
		// Retrieve all entries from db and count them
		thePlayers = playerRepository.findAll();
		long numPlayers = getCount();
		// Count number of players that selected 'available'
		long numAvail = playerRepository.countByAvailability(true);
		
		// Sort the list based on availability.
		// Players available will appear at the top.(- changes order direction)
		Collections.sort(thePlayers, new Comparator<Player>() {
			@Override
			public int compare(Player player1, Player player2) {
				return -Boolean.compare(player1.getAvailability(), player2.getAvailability());
			}
		});
		// Add the results from above to the Model.
		theModel.addAttribute("players", thePlayers);
		theModel.addAttribute("playerCount", numPlayers);
		theModel.addAttribute("numAvail", numAvail);
		
		return "index";
	}
	
	@GetMapping("/registerForm")
	public String registerForm(Model theModel) {
		
		// Create a 'blank' Player object to bind form data to
		Player thePlayer = new Player();
		theModel.addAttribute("player", thePlayer);
		
		return "registerForm";
	}
	
	@GetMapping("/info")
	public String showInfo() {
		
		return "info";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("playerId") int theId) {
		
		playerRepository.deleteById(theId);
		// Refresh page to load list based on updated model
		return "redirect:";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("player") Player thePlayer) {
		
		playerRepository.save(thePlayer);
		
		return "redirect:";
	}
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("playerId") int theId, Model theModel) {
		Player thePlayer;
		Optional<Player> temp = playerRepository.findById(theId);
		// temp is either a valid player object or null
		if(temp.isPresent())
			thePlayer = temp.get();
		else // if null
			throw new RuntimeException("No Player found by the id: "+theId);
		
		// Prepopulate the form with the selected players details
		theModel.addAttribute("player", thePlayer);
		
		return "registerForm";
	}
}
