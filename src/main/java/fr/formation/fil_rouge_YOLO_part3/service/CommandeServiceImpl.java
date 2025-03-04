package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.repository.CommandeRepository;

@Service
public class CommandeServiceImpl implements CommandeService {
	@Autowired
	CommandeRepository repo;

	@Override
	public Commande createCommande(Commande commande) {
		return null;
	}

	@Override
	public List<Commande> getAllCommandes() {
		return null;
	}

	@Override
	public Commande getCommandeById(Integer id) {
		return null;
	}

	@Override
	public Commande updateCommande(Commande commande) {
		return null;
	}

	@Override
	public Commande deleteCommande(Commande commande) {
		return null;
	}

}
