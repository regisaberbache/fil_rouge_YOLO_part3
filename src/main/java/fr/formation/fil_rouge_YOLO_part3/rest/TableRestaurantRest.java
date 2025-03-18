package fr.formation.fil_rouge_YOLO_part3.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import fr.formation.fil_rouge_YOLO_part3.rest.DemandeResaDto.DemandeResaDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantLibreDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
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

	@Autowired
	ReservationService reservationService;

	@GetMapping
	public ResponseEntity<List<TableRestaurantDTO>> getAll() {
		List<TableRestaurantDTO> lst = new ArrayList<>();
		for (TableRestaurant tableRestaurant : service.getAllTableRestaurants()) {
			lst.add(new TableRestaurantDTO(tableRestaurant));
		}
		return ResponseEntity.ok(lst);
	}
	
	
	
	@GetMapping("{idRestau}")
	public ResponseEntity<List<TableRestaurantDTO>> getTablesNonOccupees(@PathVariable("idRestau") Integer id) {
	    LocalDateTime plus120 = LocalDateTime.now().plus(120, ChronoUnit.MINUTES);
	    LocalDateTime minus120 = LocalDateTime.now().minus(120, ChronoUnit.MINUTES);
	            
	    List<TableRestaurant> tablesNonOccupees = service.getAvailableTablesFromRestaurant(minus120, plus120, id);        
	    List<TableRestaurant> tablesNonOccupeesFutures = tablesNonOccupees;
	    
	    for(TableRestaurant table : tablesNonOccupeesFutures) {
	        List<Reservation> resas = table.getReservations();
	        table.setReservations(
	            resas.stream()
	                .filter(resa -> !resa.getHoraireReservation().isBefore(minus120))
	                .collect(Collectors.toList())
	        );
	    }
	    
	    List<TableRestaurantDTO> tablesNonOccupeesFuturesDto = tablesNonOccupeesFutures.stream()
	            .map(table -> new TableRestaurantDTO(table))
	            .collect(Collectors.toList());
	    
	    return ResponseEntity.ok(tablesNonOccupeesFuturesDto);
	}
	
	
	
	/*
	 POUR TESTER, mettre dans le body un JSON de type :
	 
	  	{
		"date": "2025-03-18",
		"heure": "14:30:00",
		"nbPersonnes": 4
		}
	 
	 */
	@GetMapping("libres/{id}")
	public ResponseEntity<List<TableRestaurantLibreDTO>> getTablesLibres(@PathVariable("id") Integer id, @RequestBody DemandeResaDTO demandeResaDto) {
		Restaurant restaurant;
		try {
			restaurant = restaurantService.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		LocalDateTime horaireDemande = LocalDateTime.of(demandeResaDto.getDate(), demandeResaDto.getHeure());
		Integer nbPersonnesDemande = demandeResaDto.getNbPersonnes();
		
		List<TableRestaurant> toutesLesTables = restaurant.getTablesRestaurant();
		List<TableRestaurantLibreDTO> tablesLibresDto = toutesLesTables.stream()
			    .filter(table -> table.getRestaurant() != null)
			    .filter(table -> table.getNbPlaces() >= (nbPersonnesDemande - 1))
			    .filter(table -> {
			        LocalDateTime plus120 = horaireDemande.plus(120, ChronoUnit.MINUTES);
			        LocalDateTime minus120 = horaireDemande.minus(120, ChronoUnit.MINUTES);
			        return table.getReservations() == null || 
			               table.getReservations().stream()
			                   .noneMatch(reservation -> 
			                       reservation.getHoraireReservation().isBefore(plus120) && 
			                       reservation.getHoraireReservation().isAfter(minus120)
			                   );
			    })
			    .map(table -> new TableRestaurantLibreDTO(table))
			    .collect(Collectors.toList());
		
		return ResponseEntity.ok(tablesLibresDto);
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

	@PutMapping
	public ResponseEntity<TableRestaurantDTO> update(@RequestBody TableRestaurantDTO tableRestaurantDto) throws TableRestaurantServiceException {
		// TODO Gérer les exceptions
		service.updateTableRestaurant(tableRestaurantDto.toEntity());
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
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws TableRestaurantServiceException {
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
