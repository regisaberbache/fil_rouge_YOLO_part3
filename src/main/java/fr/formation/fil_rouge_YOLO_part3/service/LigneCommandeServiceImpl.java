package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import fr.formation.fil_rouge_YOLO_part3.repository.LigneCommandeRepository;

@Service
public class LigneCommandeServiceImpl implements LigneCommandeService {
	@Autowired
	LigneCommandeRepository repo;

	@Override
	public LigneCommande createLigneCommande(LigneCommande ligneCommande) {
		return repo.save(ligneCommande);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandes() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public LigneCommande getLigneCommandeById(Integer id) throws LigneCommandeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLigneCommande(LigneCommande ligneCommande) {
		repo.save(ligneCommande);
	}

	@Override
	public void deleteLigneCommande(LigneCommande ligneCommande) {
		// TODO Auto-generated method stub
		
	}

}
