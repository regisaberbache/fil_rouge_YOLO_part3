package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	Restaurant findByIdRestaurant(Integer idRestaurant);

}
