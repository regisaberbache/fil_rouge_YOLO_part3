package fr.formation.fil_rouge_YOLO_part3.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import bo.Reservation;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.TableRestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.TableRestaurantServiceException;

@RestController
@RequestMapping("/tables")
public class TableRestaurantRest {
	@Autowired
	TableRestaurantService service;

	@Autowired
	RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<TableRestaurantDTO>> getAll() {
		List<TableRestaurantDTO> lst = new ArrayList<>();
		for (TableRestaurant tableRestaurant : service.getAllTableRestaurants()) {
			lst.add(new TableRestaurantDTO(tableRestaurant));
		}
		return ResponseEntity.ok(lst);
	}

	@GetMapping("occupees")
	public ResponseEntity<List<TableRestaurantDTO>> getTablesOccupees() {
	    List<TableRestaurant> toutesLesTables = service.getAllTableRestaurants();
	    List<TableRestaurantDTO> tablesOccupees = toutesLesTables.stream()
	        .filter(table -> table.getReservations().stream()
	        		.anyMatch(reservation -> "arrivee".equals(reservation.getStatut())))
	        .map(table -> {
	            List<ReservationDTO> reservationsFiltrees = table.getReservations().stream()
	                .filter(reservation -> "arrivee".equals(reservation.getStatut()))
	                .map(reservation -> new ReservationDTO(reservation))
	                .collect(Collectors.toList());
	            System.out.println();
	            return new TableRestaurantDTO(table, reservationsFiltrees);
	        })
	        .collect(Collectors.toList());
	    return ResponseEntity.ok(tablesOccupees);
	}

	@GetMapping("libre/{id}")
	public ResponseEntity<List<TableRestaurantDTO>> getTablesLibres(@PathVariable("id") Integer id) {
		Restaurant restaurant;
		try {
			restaurant = restaurantService.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		List<TableRestaurant> toutesLesTables = service.getAllTableRestaurants();
		List<TableRestaurantDTO> tablesLibres = toutesLesTables.stream().filter(table -> table.getRestaurant() != null)
				.filter(table -> table.getReservations().isEmpty())
				.filter(table -> table.getRestaurant().getIdRestaurant().equals(id))
				.map(table -> new TableRestaurantDTO(table)).collect(Collectors.toList());
		return ResponseEntity.ok(tablesLibres);
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

	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Integer id) {
	    try {
	        TableRestaurant tableRestaurant = service.getTableRestaurantById(id);
	        
	        Optional<Reservation> reservationExistante = tableRestaurant.getReservations().stream()
	            .filter(reservation -> "arrivee".equals(reservation.getStatut())
	            .findFirst();
	        if (reservationExistante.isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Cette table est déjà occupée.");
	        }

	        Reservation nouvelleReservation = new Reservation();
	        nouvelleReservation.setStatut("arrivee");
	        nouvelleReservation.setHoraireReservation(LocalDateTime.now());
	        nouvelleReservation.setNbPersonne(tableRestaurant.getNbPlaces());
	        
	        tableRestaurant.getRestaurant().setIdRestaurant(id);
	        tableRestaurant.getReservations().add(nouvelleReservation);
	        

	        service.updateTableRestaurant(tableRestaurant);
	        return ResponseEntity.ok("Table " + id + " mise à jour avec une nouvelle réservation.");

	    } catch (TableRestaurantServiceException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Identifiant non trouvé.");
	    }
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
