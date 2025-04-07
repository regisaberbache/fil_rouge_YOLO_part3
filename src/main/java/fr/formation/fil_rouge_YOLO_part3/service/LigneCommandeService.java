package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;

public interface LigneCommandeService {
	LigneCommande createLigneCommande(LigneCommande ligneCommande);
	List<LigneCommande> getAllLigneCommandes();
	LigneCommande getLigneCommandeById(Integer id) throws LigneCommandeServiceException;
	void updateLigneCommande(LigneCommande ligneCommande);
	void deleteLigneCommande(LigneCommande ligneCommande);

}
