package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
	
	TableRestaurant findByIdTableRestaurant(Integer idTableRestaurant);
}
