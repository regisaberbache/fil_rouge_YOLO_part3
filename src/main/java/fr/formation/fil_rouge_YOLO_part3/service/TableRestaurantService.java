package fr.formation.fil_rouge_YOLO_part3.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

public interface TableRestaurantService {
	void createTableRestaurant(TableRestaurant tableRestaurant);
	List<TableRestaurant> getAllTableRestaurants();
	TableRestaurant getTableRestaurantById(Integer id) throws TableRestaurantServiceException;
	List<TableRestaurant> getAvailableTablesFromRestaurant(LocalDateTime startTime, LocalDateTime endTime, Integer restaurantId);
	void updateTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException;
	void deleteTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException;

}
