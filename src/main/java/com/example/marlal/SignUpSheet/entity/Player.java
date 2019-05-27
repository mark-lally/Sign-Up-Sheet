package com.example.marlal.SignUpSheet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String first_name;	
	private String last_name;
	private Boolean availability;
	
	public Player () {}

	public Player(int id, String first_name, String last_name, Boolean availability) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.availability = availability;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", availability="
				+ availability + "]";
	}

}
