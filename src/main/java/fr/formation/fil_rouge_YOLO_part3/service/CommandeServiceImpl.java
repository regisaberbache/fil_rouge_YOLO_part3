package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.repository.CommandeRepository;

@Service
public class CommandeServiceImpl implements CommandeService {
	@Autowired
	CommandeRepository repo;

	@Override
	public void createCommande(Commande commande) {
		repo.save(commande);
	}

	@Override
	public List<Commande> getAllCommandes() {
		return repo.findAll();
	}
	
	@Override
	public List<Commande> getAllCommandesByStatut(String statut) {
		return repo.findAllCommandeByCommandeStatut(statut);
	}

	@Override
	public Commande getCommandeById(Integer id) throws CommandeServiceException{
		Optional<Commande> commande = repo.findById(id);
		if(commande.isPresent()) {
			return commande.get();
		}
		else {
			throw new CommandeServiceException("Cet identifiant n'existe pas");
		}
	}

	@Override
	public void updateCommande(Commande commande) {
		repo.save(commande);
	}

	@Override
	public void deleteCommande(Commande commande) {
		repo.delete(commande);
	}

}
