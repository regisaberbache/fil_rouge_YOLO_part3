package fr.formation.fil_rouge_YOLO_part3.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;

public interface ReservationService {
	List<Reservation> getAllReservations();
	Reservation getReservationById(Integer id) throws ReservationServiceException;
	List<Reservation> getReservationsByRestaurantId(Integer id) throws ReservationServiceException, RestaurantServiceException;
	void createReservation(Reservation reservation);
	void updateReservation(Reservation reservation);
	void deleteReservation(Reservation reservation) throws ReservationServiceException;
	Integer getIdTableRestaurantById(Integer idReservation);
	List<Reservation> getFutureReservationsFromRestaurant(LocalDateTime horaire, Integer idRestaurant);
	List<ReservationDTO> getAllReservationsAsDTOs();
	ReservationDTO createReservationFromDTO(ReservationDTO reservationDto);

}
