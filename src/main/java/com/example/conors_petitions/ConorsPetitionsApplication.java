package com.example.conors_petitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConorsPetitionsApplication {
	@RequestMapping("/")
	public String hello1() {
		return "Hello";
	}

	@RequestMapping("/hello")
	public String hello2() {
		return "Hello Again";
	}

	@RequestMapping("/test")
	public String test() {
		return "This is a very important test";
	}

	public static void main(String[] args) {
		SpringApplication.run(ConorsPetitionsApplication.class, args);
	}

}
