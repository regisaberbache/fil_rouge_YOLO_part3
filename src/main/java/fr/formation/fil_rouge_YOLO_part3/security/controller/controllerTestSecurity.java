package fr.formation.fil_rouge_YOLO_part3.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerTestSecurity {
	@GetMapping("/login")
	public String login() {
		return "LOGIN";
	}
	
	@GetMapping("/accueil")
	public String accueil() {
		return "ACCUEIL";
	}

//	@GetMapping("/grand")
//	public String grand() {
//		return "GRAND";
//	}
//
//	@GetMapping("/petit")
//	public String petit() {
//		return "PETIT";
//	}
//	
//	@GetMapping("/grandEtPetit")
//	public String grandEtPetit() {
//		return "GRAND ET PETIT";
//	}
}
