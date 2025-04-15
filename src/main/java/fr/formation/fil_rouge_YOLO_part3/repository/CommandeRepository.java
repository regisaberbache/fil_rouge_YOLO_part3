package fr.formation.fil_rouge_YOLO_part3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	
	List<Commande> findAllByStatut(String statut);

	// Obligé de faire une nativeQuery car on a cassé la relation objet en ayant idTableRestaurant dans la Reservation
	// au lieu d'avoir un @ManyToOne de TableRestaurant.
	// TODO : remettre TableRestaurant dans Reservation et changer la nativeQuery par une méthode nommée
	
	@Query(value = "SELECT c.* " +
            "FROM commandes c " +
            "JOIN reservations r ON c.id_reservations = r.id " +
            "JOIN tables_restaurant t ON r.id_tables_restaurant = t.id " +
            "WHERE c.statut = :statut AND t.id_restaurants = :idRestau",
    nativeQuery = true)
	List<Commande> findAllCommandeByCommandeStatutAndIdRestaurant(@Param("idRestau")Integer idrestau, 
			@Param("statut") String statut);
}



//Commande findCommandeByIdCommande(Integer idCommande);