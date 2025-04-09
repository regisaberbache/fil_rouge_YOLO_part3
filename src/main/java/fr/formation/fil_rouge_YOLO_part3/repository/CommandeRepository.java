package fr.formation.fil_rouge_YOLO_part3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	
	Commande findCommandeByIdCommande(Integer idCommande);
	
	@Query(value = "SELECT c.* " +
            "FROM commandes c " +
            "JOIN reservations r ON c.id_reservations = r.id " +
            "JOIN tables_restaurant t ON r.id_tables_restaurant = t.id " +
            "WHERE c.statut = :statut AND t.id_restaurants = :idRestau",
    nativeQuery = true)
	List<Commande> findAllCommandeByCommandeStatutAndIdRestaurant(@Param("idRestau")Integer idrestau, @Param("statut") String statut);
	
	@Query("FROM Commande c WHERE c.statut = :statut")
	List<Commande> findAllCommandeByCommandeStatut(@Param("statut") String statut);
	
}
