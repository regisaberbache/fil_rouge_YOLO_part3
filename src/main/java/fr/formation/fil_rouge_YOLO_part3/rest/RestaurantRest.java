package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.RestaurantDto.RestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantRest {
	@Autowired
	RestaurantService service;
	
	@GetMapping
	public ResponseEntity<List<RestaurantDTO>> getAll() {
		List<RestaurantDTO> lst = new ArrayList<>();
		for (Restaurant restaurant : service.getAllRestaurants()) {
			lst.add(new RestaurantDTO(restaurant));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{id}")
	public ResponseEntity getById(@PathVariable("id") Integer id) {
		Restaurant restaurant;
		try {
			restaurant = service.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(new RestaurantDTO(restaurant));
	}
	
	@PostMapping
	public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO restaurantDto) {
		// TODO Gérer les exceptions
		service.createRestaurant(restaurantDto.toEntity());
		return ResponseEntity.ok(restaurantDto);
	}
	
	@PutMapping
	public ResponseEntity<RestaurantDTO> update(@RequestBody RestaurantDTO restaurantDto) {
		// TODO Gérer les exceptions
		service.updateRestaurant(restaurantDto.toEntity());
		return ResponseEntity.ok(restaurantDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		Restaurant restaurant;
		try {
			restaurant = service.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		service.deleteRestaurant(restaurant);
		return ResponseEntity.ok(new RestaurantDTO(restaurant));
	}
	
}
