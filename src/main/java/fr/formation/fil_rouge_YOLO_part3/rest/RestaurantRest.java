package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantRest {
	@Autowired
	RestaurantService service;
	
	@GetMapping
	public ResponseEntity<List<Restaurant>> getAll() {
		return ResponseEntity.ok(service.getAllRestaurants());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Restaurant> getById(@PathVariable("id") Integer id) {
		// TODO Gérer l'ID non présent
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
		// TODO Gérer les exceptions
		service.createRestaurant(restaurant);
		return ResponseEntity.ok(restaurant);
	}
	
	@PutMapping
	public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
		// TODO Gérer les exceptions
		service.updateRestaurant(restaurant);
		return ResponseEntity.ok(restaurant);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Restaurant> delete(@PathVariable("id") Integer id) {
		// TODO Gérer l'identifiant non présent
		Restaurant restaurant = service.getById(id);
		service.deleteRestaurant(restaurant);
		return ResponseEntity.ok(restaurant);
	}
	
}
