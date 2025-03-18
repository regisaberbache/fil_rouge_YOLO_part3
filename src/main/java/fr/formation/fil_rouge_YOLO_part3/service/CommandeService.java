package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

public interface CommandeService {
	Commande createCommande(Commande commande);
	List<Commande> getAllCommandes();
	List<Commande> getAllCommandesByStatut(String statut);
	Commande getCommandeById(Integer id) throws CommandeServiceException;
	void updateCommande(Commande commande);
	void deleteCommande(Commande commande);
	Commande ajouterPlatACommande(Integer idCommande, Integer idPlat) throws CommandeServiceException, PlatServiceException;

}
