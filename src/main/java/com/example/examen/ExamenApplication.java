package com.example.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.examen") // Scan tout le package
public class ExamenApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}
}
