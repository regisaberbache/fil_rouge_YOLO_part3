package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantRepository repo;

	@Override
	public void createRestaurant(Restaurant restaurant) {
		repo.save(restaurant);
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return repo.findAll();
	}

	@Override
	public Restaurant getById(Integer id) throws RestaurantServiceException {
		Optional<Restaurant> restaurant = repo.findById(id);
		if(restaurant.isPresent()) {
			return restaurant.get();
		}
		else {
			throw new RestaurantServiceException("Cet identifiant n'existe pas");
		}
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		repo.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Restaurant restaurant) {
		repo.delete(restaurant);
	}
}
