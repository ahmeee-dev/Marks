package com.ahmeee_dev.web_serv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String greet() {
		return "Ciao my G, stiamo steppando fortissimo";
	}

	@RequestMapping("/about")
	public String about() {
		return "yeah, you're getting it dawg";
	}
}
