package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;

public interface ReservationService {
	Reservation createReservation(Reservation reservation);
	List<Reservation> getAllReservations();
	Reservation getReservationById(Integer id);
	Reservation updateReservation(Reservation reservation);
	Reservation deleteReservation(Reservation reservation);

}
