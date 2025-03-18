package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.ReservationRepository;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationRepository repo;
	
	@Autowired
	RestaurantServiceImpl restoService;
	

	@Override
	public List<Reservation> getAllReservations() {
		return repo.findAll();
	}

	@Override
	public Reservation getReservationById(Integer id) throws ReservationServiceException {
	    return repo.findById(id)
	            .orElseThrow(() -> new ReservationServiceException("Réservation non trouvée"));
	}
	
	public List<Reservation> getReservationsByRestaurantId(Integer restaurantId) throws ReservationServiceException, RestaurantServiceException {
	    Restaurant restaurant = restoService.getById(restaurantId);
	    return repo.findAll().stream()
	            .filter(reservation -> reservation.getUtilisateur().getRestaurant().getIdRestaurant().equals(restaurantId))
	            .sorted(Comparator.comparing(Reservation::getHoraireReservation))
	            .collect(Collectors.toList());
	}

	@Override
	public void createReservation(Reservation reservation) {
		repo.save(reservation);
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
	
	public List<ReservationDTO> getAllReservationsAsDTOs() {
	    return repo.findAll().stream()
	            .map(ReservationDTO::new)
	            .collect(Collectors.toList());
	}

	public ReservationDTO createReservationFromDTO(ReservationDTO dto) {
	    Reservation reservation = dto.toEntity();
	    repo.save(reservation);
	    return new ReservationDTO(reservation);
	}

}
