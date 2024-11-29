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
				<a href="/conors_petitions/searchForm">Search for Petition</a>
				<br>
				<br>
				""";
	}

	public String signatures(Petition petition) {
		StringBuilder result = new StringBuilder();
		result.append("<h2>List of Signatures<h2><br><br>");
		for (User user : petition.users){
			result.append("<p>");
			result.append(user.getName());
			result.append(";&emsp;");
			result.append(user.getEmail());
			result.append("</p><br>");
		}
	return result.toString();
	}

	public String displayPetitions(List<Petition> petitionList) {
		StringBuilder result = new StringBuilder();
		result.append("<h3>Petition Name&emsp;&emsp;No. of Signatures</h3><br>");
		for (Petition petition : petitionList) {
            result.append("<a href=\"/conors_petitions/viewPetition?id=");
            result.append(petition.getId());
			result.append("\">");
			result.append(petition.getName());
			result.append("</a>;&emsp; : &emsp;");
			result.append(petition.getCount());
			result.append("<br>");
		}
		return result.toString();
	}

	@RequestMapping("/listPetitions")
	public String listPetitions() {
		return navBar() + displayPetitions(petitions);
	}

	@GetMapping("/viewPetition")
	public String getNew(@RequestParam int id) {
		Petition displayPetition= null;
		for (Petition petition : petitions) {
			if (petition.getId() == id) {
				displayPetition = petition;
				break;
			}
		}
		return navBar() + "<h3>Petition info: " + displayPetition.getName() + " &emsp;Number of Signatures: " + +displayPetition.getCount() +"</h3>" +  """
				<br>
				<form action="/conors_petitions/signPetition">
					<label for="user_name">User name:</label><br>
					<input type="text" id="user_name" name="user_name"><br>
					<label for="user_email">Email:</label><br>
					<input type="text" id="user_email" name="user_email"><br><br>
					<input type="submit" value="Sign Petition">
					<input type="hidden" id="id" name="id" value=
				"""
				+id+
				"""
				><br><br>
				</form>
		""";
	}

	@RequestMapping("/signPetition")
	public String signPetition(@RequestParam int id, @RequestParam String user_name,@RequestParam String user_email) {
		User newUser = new User(user_name,user_email);
		for (Petition petition : petitions) {
			if (petition.getId() == id) {
				petition.addUser(newUser);
				break;
			}
		}
		return navBar() + "Petition Signed!";
	}

	@GetMapping("/createPetitionForm")
	@ResponseBody
	public String getNew() {
		return navBar() + """
				<form action="/conors_petitions/createPetition">
					<label for="petition_name">Name of Petition:</label><br>
					<input type="text" id="petition_name" name="petition_name"><br>
					<label for="user_name">User name:</label><br>
					<input type="text" id="user_name" name="user_name"><br>
					<label for="user_email">Email:</label><br>
					<input type="text" id="user_email" name="user_email"><br><br>
					<input type="submit" value="Create Petition">
				</form>
		""" ;
	}

	@RequestMapping("/createPetition")
	public String newPetition(@RequestParam String petition_name, @RequestParam String user_name,@RequestParam String user_email) {
		User newUser = new User(user_name,user_email);
		List<User> users = new ArrayList<User>();
		users.add(newUser);
		Petition petition = new Petition(petition_name, users);
		petitions.add(petition);

		return navBar() + "Petition Created";
	}

	@GetMapping("/searchForm")
	@ResponseBody
	public String search() {
		return navBar() + """
				<form action="/conors_petitions/searchResults">
					<label for="searchTerm">Search:</label><br>
					<input type="text" id="searchTerm" name="searchTerm"><br>
					<input type="submit" value="Search Petitions">
				</form>
		""" ;
	}

	@RequestMapping("/searchResults")
	public String searchResults(@RequestParam String searchTerm) {
		List<Petition> searchResults = new ArrayList<>();
		for (Petition petition : petitions) {
			if (petition.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
				searchResults.add(petition);
			}
		}
		return navBar() +  displayPetitions(searchResults);
	}

	@PostConstruct
	public void initialise() {
		List<User> users = new ArrayList<User>();
		List<User> users2 = new ArrayList<User>();
		List<User> users3 = new ArrayList<User>();
		List<User> users4 = new ArrayList<User>();
		List<User> users5 = new ArrayList<User>();
		List<User> users6 = new ArrayList<User>();
		User newUser = new User("Conor","@Conor");
		users.add(newUser);
		users2.add(newUser);
		users4.add(newUser);
		users6.add(newUser);
		newUser = new User("John","@John");
		users.add(newUser);
		users2.add(newUser);
		users3.add(newUser);
		users4.add(newUser);
		users5.add(newUser);
		Petition petition1 = new Petition("Down With Sheep", users);
		petitions.add(petition1);
		Petition petition2 = new Petition("Up With Sheep", users2);
		petitions.add(petition2);
		Petition petition3 = new Petition("Sheep for President", users3);
		petitions.add(petition3);
		Petition petition4 = new Petition("Cows Against Sheep", users4);
		petitions.add(petition4);
		Petition petition5 = new Petition("Cows against Horses", users5);
		petitions.add(petition5);
		Petition petition6 = new Petition("Pigs for all", users6);
		petitions.add(petition6);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConorsPetitionsApplication.class, args);
	}
}
