package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
	private Integer idCommande;
	private String statut;
	private List<LigneCommande> lignes;
	
	public CommandeDTO(Commande commande) {
		this.idCommande = commande.getIdCommande();
		this.statut = commande.getStatut();
		this.lignes = commande.getLignes();		
	}
	
	public Commande toEntity() {
		Commande commande = new Commande();
		commande.setIdCommande(idCommande);
		commande.setStatut(statut);
		commande.setLignes(lignes);
		return commande;
	}
}
