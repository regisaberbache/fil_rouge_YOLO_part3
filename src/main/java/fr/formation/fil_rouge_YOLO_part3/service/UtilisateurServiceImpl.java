package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	UtilisateurRepository repo;

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		repo.save(utilisateur);
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		return repo.findAll();
	}

	@Override
	public Utilisateur getUtilisateurById(Integer id) {
		return repo.findUtilisateurByIdUtilisateur(id);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		repo.save(utilisateur);
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		repo.delete(utilisateur);
	}
	
	

}
