package fr.formation.fil_rouge_YOLO_part3.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
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

	@GetMapping("{id}")
	public ResponseEntity<List<TableRestaurantDTO>> getTablesNonOccupees(@PathVariable("id") Integer id) {
		Restaurant restaurant;
		try {
			restaurant = restaurantService.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		List<TableRestaurant> toutesLesTables = service.getAllTableRestaurants();
		List<TableRestaurantDTO> tablesNonOccupees = toutesLesTables.stream()
				.filter(table -> table.getRestaurant() != null).filter(table -> table.getReservations().isEmpty())
				.filter(table -> table.getRestaurant().getIdRestaurant().equals(id))
				.map(table -> new TableRestaurantDTO(table)).collect(Collectors.toList());
		return ResponseEntity.ok(tablesNonOccupees);
	}

	@GetMapping("libres/{id}")
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

	@GetMapping("occupees")
	public ResponseEntity<List<TableRestaurantDTO>> getTablesOccupees() {
		List<TableRestaurant> toutesLesTables = service.getAllTableRestaurants();
		List<TableRestaurantDTO> tablesOccupees = toutesLesTables.stream()
				.filter(table -> table.getReservations() != null && !table.getReservations().isEmpty() && table
						.getReservations().stream().anyMatch(reservation -> "arrivee".equals(reservation.getStatut())))
				.map(table -> {
					List<ReservationDTO> reservationsFiltrees = table.getReservations().stream()
							.filter(reservation -> "arrivee".equals(reservation.getStatut()))
							.map(reservation -> new ReservationDTO(reservation)).collect(Collectors.toList());
					return new TableRestaurantDTO(table, reservationsFiltrees);
				}).collect(Collectors.toList());
		return ResponseEntity.ok(tablesOccupees);
	}


	@PostMapping
	public ResponseEntity<TableRestaurantDTO> create(@RequestBody TableRestaurantDTO tableRestaurantDto) {
		// TODO Gérer les exceptions
		service.createTableRestaurant(tableRestaurantDto.toEntity());
		return ResponseEntity.ok(tableRestaurantDto);
	}


	@PutMapping("{id}")
	public ResponseEntity<Object> updateTableStatutToOccupees(@PathVariable("id") Integer id) {
		try {
			// Récupérer la table par ID
			TableRestaurant tableRestaurant = service.getTableRestaurantById(id);
		
			// Vérifier si la table a des réservations
			if (tableRestaurant.getReservations() != null && !tableRestaurant.getReservations().isEmpty()) {
				// Mettre à jour le statut de la réservation correspondant à la date actuelle
				boolean reservationUpdated = false;
				
	            LocalTime currentTime = LocalTime.now();
	            LocalTime plusOneHour = currentTime.plus(60, ChronoUnit.MINUTES);
	            LocalTime minusOneHour = currentTime.minus(60, ChronoUnit.MINUTES);
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
				
				for (Reservation reservation : tableRestaurant.getReservations()) {
					LocalTime horaireReservation = reservation.getHoraireReservation().toLocalTime();
					horaireReservation.format(timeFormatter);
					if (reservation.getHoraireReservation().toLocalDate().equals(LocalDate.now())
							&& (horaireReservation.isBefore(plusOneHour))
							&& (horaireReservation.isAfter(minusOneHour))) {
						reservation.setStatut("occupee");
						reservationUpdated = true;
						break; // Sortir de la boucle après avoir trouvé et mis à jour la réservation
					}
				}

				if (!reservationUpdated) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Aucune réservation trouvée pour la date actuelle.");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La table n'a aucune réservation.");
			}

			// Mettre à jour la table dans le service
			service.updateTableRestaurant(tableRestaurant);

			// Retourner la réponse avec le DTO de la table mise à jour
			return ResponseEntity.ok(new TableRestaurantDTO(tableRestaurant));
		} catch (TableRestaurantServiceException e) {
			// Gérer le cas où la table n'est pas trouvée
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
