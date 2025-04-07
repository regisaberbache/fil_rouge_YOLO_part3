package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

public interface TableRestaurantService {
	void createTableRestaurant(TableRestaurant tableRestaurant);
	List<TableRestaurant> getAllTableRestaurants();
	TableRestaurant getTableRestaurantById(Integer id) throws TableRestaurantServiceException;
	void updateTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException;
	void deleteTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException;

}
