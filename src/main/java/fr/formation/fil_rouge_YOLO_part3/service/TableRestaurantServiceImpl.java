package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.TableRestaurantRepository;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {
	@Autowired
	TableRestaurantRepository repo;

	@Override
	public TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableRestaurant> getAllTableRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableRestaurant getTableRestaurantById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableRestaurant updateTableRestaurant(TableRestaurant tableRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableRestaurant deleteTableRestaurant(TableRestaurant tableRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
