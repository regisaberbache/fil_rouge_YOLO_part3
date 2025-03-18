package fr.formation.fil_rouge_YOLO_part3.rest;

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
	    return ResponseEntity.ok(service.getAllReservationsAsDTOs());
	}

	@GetMapping("/{idRestau}")
	public ResponseEntity<List<ReservationDTO>> getReservationsByIdRestaurant(@PathVariable("idRestau") Integer id) throws RestaurantServiceException {
		try {
		List<ReservationDTO> reservations = service.getReservationsByRestaurantId(id).stream()
				.map(ReservationDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(reservations);
	    } catch (ReservationServiceException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}

	
	@PostMapping
	public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDto) {
	    return ResponseEntity.ok(service.createReservationFromDTO(reservationDto));
	}


	@PutMapping
	public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDto) {
		// TODO Gérer les exceptions
		service.updateReservation(reservationDto.toEntity());
		return ResponseEntity.ok(reservationDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
	    try {
	        Reservation reservation = service.getReservationById(id);
	        service.deleteReservation(reservation);
	        return ResponseEntity.ok(new ReservationDTO(reservation));
	    } catch (ReservationServiceException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

}
