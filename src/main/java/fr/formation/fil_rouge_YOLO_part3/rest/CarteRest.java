package fr.formation.fil_rouge_YOLO_part3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.exceptions.RestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carte")
public class CarteRest {
	@Autowired
	RestaurantService restoService;
	
	@GetMapping("{idResto}")
	public ResponseEntity<Object> getCarteByRestaurant(@PathVariable("idResto") Integer idResto) {
		Restaurant restaurant;
		try {
			restaurant = restoService.getById(idResto);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant non trouvé");
		}
		
		return ResponseEntity.ok(restaurant.getCarte());
	}	
}