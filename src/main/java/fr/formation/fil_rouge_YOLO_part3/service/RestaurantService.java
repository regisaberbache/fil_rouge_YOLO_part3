package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;

public interface RestaurantService {
	Restaurant createRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants();
	Restaurant updateRestaurant(Restaurant restaurant);
	Restaurant deleteRestaurant(Restaurant restaurant);
	Restaurant getById(Integer id);
	
}
