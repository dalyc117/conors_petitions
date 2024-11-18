package com.example.conors_petitions;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ConorsPetitionsApplication {
	@RequestMapping("/")
	public String hello1() {
		return "<a href=\"/conors_petitions/listPetitions\">Click here to List!</a>";
	}

	static List<Object> petitions = new ArrayList<>();

	@RequestMapping("/listPetitions")
	public String listPetitions() {
		return petitions.toString();
	}

	@RequestMapping("/test")
	public String test() {
		return "This is a very very important test";
	}
	@PostConstruct
	public void initialise() {
		String[] users = {"Conor", "Conor2"};
		Petition petition1 = new Petition("Down With Sheep", users);
		petitions.add(petition1);
		Petition petition2 = new Petition("Up With Sheep", users);
		petitions.add(petition2);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConorsPetitionsApplication.class, args);
	}
}
