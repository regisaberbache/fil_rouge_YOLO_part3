package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;

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
	public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(new ReservationDTO(service.getReservationById(id)));
	}
		
}
