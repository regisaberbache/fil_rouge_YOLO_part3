package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	Reservation findByIdReservation(Integer idReservation);

}
