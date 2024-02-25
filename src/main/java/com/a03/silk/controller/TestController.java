package com.a03.silk.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = "https://silk-purwa.up.railway.app")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

	@GetMapping("/api/test")
	public String test() {
		return "This is GET request for testing Backend propenfroz. The current time is " + new Date();
	}
}
