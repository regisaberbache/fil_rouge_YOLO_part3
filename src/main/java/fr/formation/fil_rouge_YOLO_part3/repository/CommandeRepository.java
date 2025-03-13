package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	
	Commande findCommandeByIdCommande(Integer idCommande);
}
