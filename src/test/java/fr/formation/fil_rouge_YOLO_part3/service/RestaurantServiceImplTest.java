package fr.formation.fil_rouge_YOLO_part3.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.fil_rouge_YOLO_part3.entity.Carte;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Utiliser la BDD SQLServer YOLOTEST
class RestaurantServiceImplTest {
	@Autowired
	RestaurantServiceImpl restaurantService;
	
    @BeforeEach
    void setUp() {
    }
    
	//Récupération de tous les restaurants
	@Test
	void testGetAllRestaurants() {
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		assertFalse(restaurants.isEmpty());
		assertEquals(15, restaurants.size());
	}
	
	// Récupération par ID
	@Test
	void testGetRestaurantById() throws RestaurantServiceException {
		Restaurant restaurant = restaurantService.getById(1);
		assertNotNull(restaurant);
		assertEquals("Pizza YOLO Nantes", restaurant.getNom());
	}
	
	 // Gestion d'une erreur si l'ID n'existe pas
	@Test
	void testGetRestaurantById_NotFound() {
		Exception exception = assertThrows(RestaurantServiceException.class, () -> {
			restaurantService.getById(99);
		});
		assertEquals("Cet identifiant n'existe pas", exception.getMessage());
	}
	
    // Création d'un restaurant
	@Test
	void testCreateRestaurant() {
		Carte carte = new Carte();
		carte.setIdCarte(1);
		
		Restaurant restaurant = new Restaurant("Pizza YOLO Test6", "Adresse Bidon", "email@test.fr", "www.urlImageTest.com", carte);
		restaurantService.createRestaurant(restaurant);
		
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		assertEquals(15, restaurants.size());
	}
		
	//Mise à jour d'un restaurant
	@Test
	void testUpdateRestaurant() throws RestaurantServiceException {
		Restaurant restaurant = restaurantService.getById(6);
		restaurant.setAdresse("Adresse modifiée mais tjr bidon");
	    // Sauvegarde de l'entité après modification
	    restaurantService.updateRestaurant(restaurant);
		
		Restaurant updatedRestaurant = restaurantService.getById(6);
		assertEquals("Adresse modifiée mais tjr bidon", updatedRestaurant.getAdresse());
	}
	
}
