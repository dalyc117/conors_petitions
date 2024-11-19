package com.example.conors_petitions;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ConorsPetitionsApplication {
	@RequestMapping("/")
	public String hello1() {
		return "<a href=\"/conors_petitions/listPetitions\">Click here to List!</a>";
	}

	static List<Petition> petitions = new ArrayList<>();

	@RequestMapping("/listPetitions")
	public String listPetitions() {
		return petitions.toString();
	}

	@RequestMapping("/test")
	public String test() {
		return "This is a very very important test";
	}

	@RequestMapping("/viewPetition")
	public String getNew(@RequestParam int id) {
		Petition displayPetition= null;
		for (Petition petition : petitions) {
			if (petition.getId() == id) {
				displayPetition = petition;
				break;
			}
		}

		return "<h1>Petition info: " + displayPetition + "</h1>";
	}

	@GetMapping("/createPetitionForm")
	@ResponseBody
	public String getNew(@RequestParam String id) {
		return """
				<form action="/createPetition">
					<label for="petition_name">Name of Petition:</label><br>
					<input type="text" id="petition_name" name="petition_name"><br>
					<label for="user_name">User name:</label><br>
					<input type="text" id="user_name" name="user_name"><br><br>
					<input type="submit" value="Create Petition">
				</form>\s
		""" ;
	}

	@RequestMapping("/createPetition")
	public String newPetition(@RequestParam int id) {
		Petition displayPetition= null;
		for (Petition petition : petitions) {
			if (petition.getId() == id) {
				displayPetition = petition;
				break;
			}
		}

		return "Petition Created";
	}

	@PostMapping("/posting")
	public String po(@RequestParam("someName") String someName) {
		return "hello" + someName;
	}

	@PostConstruct
	public void initialise() {
		String[] users = {"Conor", "Conor2"};
		Petition petition1 = new Petition("Down With Sheep", users, 1);
		petitions.add(petition1);
		Petition petition2 = new Petition("Up With Sheep", users, 2);
		petitions.add(petition2);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConorsPetitionsApplication.class, args);
	}
}
