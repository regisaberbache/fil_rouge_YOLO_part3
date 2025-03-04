package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationRepository repo;

	@Override
	public Reservation createReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservationById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation deleteReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

}
