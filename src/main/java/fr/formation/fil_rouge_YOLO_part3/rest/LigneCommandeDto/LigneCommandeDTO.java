package fr.formation.fil_rouge_YOLO_part3.rest.LigneCommandeDto;

import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeDTO {
	private Integer idLigneCommande;
	private Integer idPlat;
	private Integer idCommande;
	private Integer quantite;
	
	
	public LigneCommandeDTO(LigneCommande ligneCommande) {
		this.idLigneCommande = ligneCommande.getIdLigneCommande();
		this.idPlat = ligneCommande.getPlat().getIdPlat();
		this.idCommande = ligneCommande.getCommande().getIdCommande();
		this.quantite = ligneCommande.getQuantite();
	}
	
	
//	public LigneCommande toEntity() {
//		
//		return null;
//	}
	

}
