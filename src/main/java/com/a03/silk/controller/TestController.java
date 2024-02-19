package com.a03.silk.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://silk-purwa.up.railway.app")
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "This is GET request for testing backend. The current time is " + new Date();
	}
}
