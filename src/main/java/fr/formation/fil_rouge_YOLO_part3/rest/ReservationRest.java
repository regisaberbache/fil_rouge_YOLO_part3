package fr.formation.fil_rouge_YOLO_part3.rest;

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

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationServiceException;

@RestController
@RequestMapping("/reservations")
public class ReservationRest {
	@Autowired
	ReservationService service;

	@GetMapping
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		List<ReservationDTO> listeReservations = service.getAllReservations()
				.stream().map(r-> new ReservationDTO(r))
				.toList();
		return ResponseEntity.ok(listeReservations);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getReservationById(@PathVariable("id") Integer id) throws ReservationServiceException {
		Reservation reservation;
		try {
			reservation = service.getReservationById(id);
		} catch (ReservationServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(new ReservationDTO(reservation));
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
	public ResponseEntity delete(@PathVariable("id") Integer id) throws ReservationServiceException {
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
