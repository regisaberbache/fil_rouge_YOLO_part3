package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;

public interface CommandeService {
	Commande createCommande(Commande commande);
	List<Commande> getAllCommandes();
	Commande getCommandeById(Integer id);
	Commande updateCommande(Commande commande);
	Commande deleteCommande(Commande commande);

}
