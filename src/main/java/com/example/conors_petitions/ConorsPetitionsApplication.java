package com.example.conors_petitions;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	public String navBar() {
		return """
				<a href="/conors_petitions/listPetitions">List of Petitions</a>
				&emsp;
				<a href="/conors_petitions/createPetitionForm">Create New Petition</a>
				&emsp;
				<a href="/conors_petitions/listPetitions">Search for Petition</a>
				<br>
				""";
	}

	@RequestMapping("/listPetitions")
	public String listPetitions() {
		return navBar() + petitions.toString();
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

		return navBar() + "<h1>Petition info: " + displayPetition + "</h1>";
	}

	@GetMapping("/createPetitionForm")
	@ResponseBody
	public String getNew() {
		return navBar() + """
				<form action="/conors_petitions/createPetition">
					<label for="petition_name">Name of Petition:</label><br>
					<input type="text" id="petition_name" name="petition_name"><br>
					<label for="user_name">User name:</label><br>
					<input type="text" id="user_name" name="user_name"><br><br>
					<input type="submit" value="Create Petition">
				</form>
		""" ;
	}

	@RequestMapping("/createPetition")
	public String newPetition(@RequestParam String petition_name, @RequestParam String user_name) {
		String[] users = {user_name};
		Petition petition = new Petition(petition_name, users);
		petitions.add(petition);

		return navBar() + "Petition Created";
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
