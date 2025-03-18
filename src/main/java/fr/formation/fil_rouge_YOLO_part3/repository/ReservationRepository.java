package fr.formation.fil_rouge_YOLO_part3.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.horaireReservation > :currentDateTime")
    List<Reservation> findAllFutureReservations(@Param("currentDateTime") LocalDateTime currentDateTime);
	
	Reservation findByIdReservation(Integer idReservation);
	
	@Query("SELECT r.idTableRestaurant FROM Reservation r WHERE r.id = :idReservation")
    Integer findIdTableRestaurantById(@Param("idReservation") Integer idReservation);
	
	@Query(value = "SELECT * FROM reservations WHERE horaire_reservation >= :now AND id_restaurants = :idrestaurant ORDER BY horaire_reservation ASC", nativeQuery = true)
    List<Reservation> findFutureReservationsFromRestaurant(@Param("now") LocalDateTime now, @Param("idrestaurant") Integer idRestaurant);

}
