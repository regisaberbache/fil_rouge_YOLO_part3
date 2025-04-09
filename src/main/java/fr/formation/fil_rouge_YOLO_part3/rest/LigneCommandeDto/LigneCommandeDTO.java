package fr.formation.fil_rouge_YOLO_part3.rest.LigneCommandeDto;

import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import fr.formation.fil_rouge_YOLO_part3.entity.Plat;
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
	private Plat plat;
	
	
	public LigneCommandeDTO(LigneCommande ligneCommande) {
		this.idLigneCommande = ligneCommande.getIdLigneCommande();
		this.idPlat = ligneCommande.getPlat().getIdPlat();
		this.idCommande = ligneCommande.getCommande().getIdCommande();
		this.quantite = ligneCommande.getQuantite();
		this.plat = ligneCommande.getPlat();
	}
	
	
//	public LigneCommande toEntity() {
//		
//		return null;
//	}
	

}
