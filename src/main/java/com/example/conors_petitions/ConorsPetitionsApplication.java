package com.example.conors_petitions;

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
		return "<a href=\"\\listPetitions\">Click here to List!</a>";
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

	public static void main(String[] args) {
		String[] users = {"Conor", "Conor2"};
		Petition petition1 = new Petition("Down With Sheep", users);
		petitions.add(petition1);
		SpringApplication.run(ConorsPetitionsApplication.class, args);
	}
}
