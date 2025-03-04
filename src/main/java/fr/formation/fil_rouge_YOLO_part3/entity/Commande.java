package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "commandes")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String statut;
	
	@OneToMany
	@JoinColumn(name = "id_commandes")
	private List<LigneCommande> lignes;

	public Commande(String statut, List<LigneCommande> lignes) {
		this.statut = statut;
		this.lignes = lignes;
	}
		

}
