package fr.formation.fil_rouge_YOLO_part3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;

@SpringBootApplication
public class FilRougeYoloPart3Application implements CommandLineRunner {
	
	@Autowired
	RestaurantService restaurantService;
	
	public static void main(String[] args) {
		SpringApplication.run(FilRougeYoloPart3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Restaurant> restos = restaurantService.getAllRestaurants();
		restos.forEach(System.out::println);
			
		//Restaurant resto = restaurantService.getByIdRestaurant(1);
			
		//Carte carte = resto.getCarte();
			
		//System.out.println(carte.getNom());		
	}

}
