package fr.formation.fil_rouge_YOLO_part3.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
	
	TableRestaurant findByIdTableRestaurant(Integer idTableRestaurant);
	
	@Query(value = "SELECT * FROM tables_restaurant tr " +
            "WHERE tr.id_restaurants = :idRestaurant " +
            "AND NOT EXISTS ( " +
            "    SELECT * FROM reservations r " +
            "    WHERE r.id_tables_restaurant = tr.id " +
            "    AND (r.horaire_reservation <= :endTime " +
            "    AND r.horaire_reservation >= :startTime)" +
            ")",
            nativeQuery = true)
		List<TableRestaurant> findAvailableTablesFromRestaurant(@Param("startTime") LocalDateTime startTime, 
		                                          @Param("endTime") LocalDateTime endTime, 
		                                          @Param("idRestaurant") Integer idRestaurant);
	
}
