package fr.formation.fil_rouge_YOLO_part3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asso_commandes_plats")
public class LigneCommande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idLigneCommande;
	
	@ManyToOne
	@JoinColumn(name = "id_plats")
	private Plat plat;
	
	private Integer quantite;

	public LigneCommande(Plat plat, Integer quantite) {
		this.plat = plat;
		this.quantite = quantite;
	}

}
