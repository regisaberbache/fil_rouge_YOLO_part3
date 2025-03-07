package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;

public interface UtilisateurService {
	void createUtilisateur(Utilisateur utilisateur);
	List<Utilisateur> getAllUtilisateurs();
	Utilisateur getUtilisateurById(Integer id);
	void updateUtilisateur(Utilisateur utilisateur);
	void deleteUtilisateur(Utilisateur utilisateur);

}
