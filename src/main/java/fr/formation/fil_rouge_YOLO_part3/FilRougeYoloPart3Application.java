package fr.formation.fil_rouge_YOLO_part3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD

@SpringBootApplication
public class FilRougeYoloPart3Application {
=======
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
>>>>>>> 83a6ee45d5b6f7b89bb716d488752ee416e409f4

@SpringBootApplication
public class FilRougeYoloPart3Application implements CommandLineRunner {
	
	@Autowired
	RestaurantService restaurantService;
	
	public static void main(String[] args) {
		SpringApplication.run(FilRougeYoloPart3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
