package com.a03.silk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("------------------ APPLICATION SUCCESSFULLY STARTED ------------------");
	}
}
