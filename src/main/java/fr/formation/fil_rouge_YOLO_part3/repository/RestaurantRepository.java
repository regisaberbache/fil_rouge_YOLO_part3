package fr.formation.fil_rouge_YOLO_part3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	

	Restaurant findById(Integer id);

}
