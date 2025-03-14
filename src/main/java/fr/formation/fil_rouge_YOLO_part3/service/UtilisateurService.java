package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;

public interface UtilisateurService {
	/**
	 * Persist an {@link Utilisateur} in the database.
	 * 
	 * @return the {@link Utilisateur} entity 
	 *         that was inserted in the database.
	 */
	Utilisateur createUtilisateur(Utilisateur utilisateur);
	
	/**
	 * Finds all {@link Utilisateur} existing in the database.
	 * 
	 * @return a list of {@link Utilisateur} entities, 
	 *         or an empty list if no Utilisateur is found.
	 */
	List<Utilisateur> getAllUtilisateurs();
	
	/**
	 * Finds an {@link Utilisateur} entity by Id.
	 * 
	 * @return the {@link Utilisateur} entity 
	 *         found in the database, matching that Id.
	 */
	Utilisateur getUtilisateurById(Integer id) throws UtilisateurServiceException;
	
	/**
	 * Updated an {@link Utilisateur} entity in the database.
	 * 
	 * @param utilisateur
	 */
	void updateUtilisateur(Utilisateur utilisateur);
	
	/**
	 * Deletes an {@link Utilisateur} entity from the database.
	 * 
	 * @param utilisateur
	 */
	void deleteUtilisateur(Utilisateur utilisateur);

}
