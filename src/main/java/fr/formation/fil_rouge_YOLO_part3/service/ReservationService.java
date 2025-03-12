package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;

public interface ReservationService {
	void createReservation(Reservation reservation);
	List<Reservation> getAllReservations();
	Reservation getReservationById(Integer id) throws ReservationServiceException;
	void updateReservation(Reservation reservation);
	void deleteReservation(Reservation reservation) throws ReservationServiceException;

}
