package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	Reservation findByIdReservation(Integer idReservation);
	
	@Query("SELECT r.idTableRestaurant FROM Reservation r WHERE r.id = :idReservation")
    Integer findIdTableRestaurantById(@Param("idReservation") Integer idReservation);

}
