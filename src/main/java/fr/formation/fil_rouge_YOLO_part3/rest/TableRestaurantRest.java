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

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.service.TableRestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.TableRestaurantServiceException;

@RestController
@RequestMapping("/tables")
public class TableRestaurantRest {
	@Autowired
	TableRestaurantService service;
	
	@GetMapping
	public ResponseEntity<List<TableRestaurantDTO>> getAll() {
		List<TableRestaurantDTO> lst = new ArrayList<>();
		for (TableRestaurant tableRestaurant : service.getAllTableRestaurants()) {
			lst.add(new TableRestaurantDTO(tableRestaurant));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
		TableRestaurant tableRestaurant;
		try {
			tableRestaurant = service.getTableRestaurantById(id);
		} catch (TableRestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(new TableRestaurantDTO(tableRestaurant));
	}
	
	@PostMapping
	public ResponseEntity<TableRestaurantDTO> create(@RequestBody TableRestaurantDTO tableRestaurantDto) {
		// TODO Gérer les exceptions
		service.createTableRestaurant(tableRestaurantDto.toEntity());
		return ResponseEntity.ok(tableRestaurantDto);
	}
	
	@PutMapping
	public ResponseEntity<TableRestaurantDTO> update(@RequestBody TableRestaurantDTO tableRestaurantDto) {
		// TODO Gérer les exceptions
		service.updateTableRestaurant(tableRestaurantDto.toEntity());
		return ResponseEntity.ok(tableRestaurantDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		TableRestaurant tableRestaurant;
		try {
			tableRestaurant = service.getTableRestaurantById(id);
		} catch (TableRestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		service.deleteTableRestaurant(tableRestaurant);
		return ResponseEntity.ok(new TableRestaurantDTO(tableRestaurant));
	}
}
