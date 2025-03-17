package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.Comparator;
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
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;

@RestController
@RequestMapping("/reservations")
public class ReservationRest {
	@Autowired
	ReservationService service;

	@Autowired
	RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		List<ReservationDTO> listeReservations = service.getAllReservations().stream()
				.map(reservation -> new ReservationDTO(reservation)).toList();
		return ResponseEntity.ok(listeReservations);
	}

	// Recherche de l'ID du restaurant puis on récupère la liste des réservations
	// sur laquelle on filtre pour rechercher les réservations en fonction de l'Id
	// du restaurant et trier selon la date et l'heure de la reservation.
	// Enfin on transforme (map) les réservations en object ReservationDTO que l'on repasse en List.
	@GetMapping("/{id}")
	public ResponseEntity<List<ReservationDTO>> getReservationsByIdRestaurant(@PathVariable("id") Integer id)
			throws ReservationServiceException {
		Restaurant restaurant;
		try {
			restaurant = restaurantService.getById(id);
		} catch (RestaurantServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		List<ReservationDTO> listeReservations = service.getAllReservations().stream()
				.filter(reservation -> reservation.getUtilisateur().getRestaurant().getIdRestaurant().equals(id))
				.sorted(Comparator.comparing(reservation -> reservation.getHoraireReservation()))
				.map(reservation -> new ReservationDTO(reservation))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listeReservations);
	}

	@PostMapping
	public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDto) {
		// TODO Gérer les exceptions
		service.createReservation(reservationDto.toEntity());
		return ResponseEntity.ok(reservationDto);
	}

	@PutMapping
	public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDto) {
		// TODO Gérer les exceptions
		service.updateReservation(reservationDto.toEntity());
		return ResponseEntity.ok(reservationDto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws ReservationServiceException {
		Reservation reservation;
		try {
			reservation = service.getReservationById(id);
		} catch (ReservationServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		service.deleteReservation(reservation);
		return ResponseEntity.ok(new ReservationDTO(reservation));
	}
}
