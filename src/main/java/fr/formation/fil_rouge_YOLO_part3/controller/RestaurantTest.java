package fr.formation.fil_rouge_YOLO_part3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.fil_rouge_YOLO_part3.FilRougeYoloPart3Application;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;

@SpringBootApplication
public class RestaurantTest implements CommandLineRunner {
	@Autowired
	RestaurantService restaurantService;

	public static void main(String[] args) {
		SpringApplication.run(FilRougeYoloPart3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Restaurant> restos = restaurantService.getAllRestaurants();
		restos.forEach(System.out::println);
		
		Restaurant resto = restaurantService.getById(1);
		
		String carteNom = resto.getCarte().getNom();
		System.out.println("Le nom de la carte du restaurant n°1 est : " + carteNom);
			
	}
	
}
