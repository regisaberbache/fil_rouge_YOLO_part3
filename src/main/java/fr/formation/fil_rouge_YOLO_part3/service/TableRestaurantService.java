package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

public interface TableRestaurantService {
	TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant);
	List<TableRestaurant> getAllTableRestaurants();
	TableRestaurant getTableRestaurantById(Integer id);
	TableRestaurant updateTableRestaurant(TableRestaurant tableRestaurant);
	TableRestaurant deleteTableRestaurant(TableRestaurant tableRestaurant);

}
