package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantRepository repo;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return repo.findAll();
	}

	@Override
	public Restaurant getById(Integer id) {
		return repo.findByIdRestaurant(id);
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant deleteRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return null;
	}

}
