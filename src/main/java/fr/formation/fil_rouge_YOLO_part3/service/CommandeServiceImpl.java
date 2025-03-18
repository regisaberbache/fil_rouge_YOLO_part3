package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import fr.formation.fil_rouge_YOLO_part3.entity.Plat;
import fr.formation.fil_rouge_YOLO_part3.repository.CommandeRepository;

@Service
public class CommandeServiceImpl implements CommandeService {
	@Autowired
	CommandeRepository repo;
	
	@Autowired
	PlatService platService;
	
	@Autowired
	LigneCommandeService ligneCommandeService;

	@Override
	public Commande createCommande(Commande commande) {
		return repo.save(commande);
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

	public Integer ajouterPlatACommande(Integer idCommande, Integer idPlat) throws CommandeServiceException, PlatServiceException {
		Commande commande = getCommandeById(idCommande);
	    Plat plat = platService.getPlatById(idPlat);
	    
	    Integer qtePlatFinale = null;
		
	    List<LigneCommande> lignesCommandes = commande.getLignes();
	    LigneCommande existingLigne = null;
	    
	    if (lignesCommandes != null) {
	        for (LigneCommande ligne : lignesCommandes) {
	            if (plat.equals(ligne.getPlat())) {
	                existingLigne = ligne;
	                break;
	            }
	        }
	    }

	    if (existingLigne != null) {
	        existingLigne.setQuantite(existingLigne.getQuantite() + 1);
	        ligneCommandeService.updateLigneCommande(existingLigne);
	        qtePlatFinale = existingLigne.getQuantite();
	    } else {
	        LigneCommande nouvelleLigne = new LigneCommande();
	        nouvelleLigne.setCommande(commande);;
	        nouvelleLigne.setPlat(plat);
	        nouvelleLigne.setQuantite(1);
	        if (lignesCommandes == null) {
	            commande.setLignes(new ArrayList<>());
	        }
	        commande.getLignes().add(nouvelleLigne);
	        ligneCommandeService.updateLigneCommande(nouvelleLigne);
	        qtePlatFinale = 1;
	    }
	    
	    repo.save(commande);
	    return qtePlatFinale;
	}
	
}
