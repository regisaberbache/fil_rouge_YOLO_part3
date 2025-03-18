package fr.formation.fil_rouge_YOLO_part3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationRepository repo;

	@Override

	public void createReservation(Reservation reservation) {
		repo.save(reservation);

	}

	@Override
	public List<Reservation> getAllReservations() {
		return repo.findAll();
	}

	@Override
	public Reservation getReservationById(Integer id) throws ReservationServiceException {
		Optional<Reservation> reservation = repo.findById(id);
		if(reservation.isPresent()) {
			return reservation.get();
		}
		else {
			throw new ReservationServiceException("Cet identifiant n'existe pas");
		}
	}

	@Override
	public void updateReservation(Reservation reservation) {
		repo.save(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws ReservationServiceException {
		repo.delete(reservation);
	}

	@Override
	public Integer getIdTableRestaurantById(Integer idReservation) {
		return repo.findIdTableRestaurantById(idReservation);
	}
	
	@Override
	public List<Reservation> getFutureReservationsFromRestaurant(LocalDateTime horaire, Integer idRestaurant) {
		return repo.findFutureReservationsFromRestaurant(LocalDateTime.now(), idRestaurant);
	}



}
