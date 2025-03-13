package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;

public interface RestaurantService {
	void createRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants();
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(Restaurant restaurant);
	Restaurant getById(Integer id) throws RestaurantServiceException;
	
}
