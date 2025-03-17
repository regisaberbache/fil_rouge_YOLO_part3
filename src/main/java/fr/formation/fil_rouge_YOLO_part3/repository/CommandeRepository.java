package fr.formation.fil_rouge_YOLO_part3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	
	Commande findCommandeByIdCommande(Integer idCommande);
	
	@Query("FROM Commande c WHERE c.statut = :statut")
	List<Commande> findAllCommandeByCommandeStatut(@Param("statut") String statut);
	
}
