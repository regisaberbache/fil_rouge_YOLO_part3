package fr.formation.fil_rouge_YOLO_part3.rest.PlatDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Categorie;
import fr.formation.fil_rouge_YOLO_part3.entity.Plat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatDTO {
	private Integer idPlat;
	private String nom;
	private Double prix;
	private String description;
	private Categorie categorie;
	
	public PlatDTO(Plat plat) {
		this.idPlat = plat.getIdPlat();
		this.nom = plat.getNom();
		this.prix = plat.getPrix();
		this.description = plat.getDescription();
		this.categorie = plat.getCategorie();
	}
	
	public Plat toEntity() {
		Plat plat = new Plat();
		plat.setNom(nom);
		plat.setPrix(prix);
		plat.setDescription(description);
		plat.setCategorie(categorie);
		return plat;
	}	
}