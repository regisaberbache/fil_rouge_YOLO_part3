package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cartes")
public class Carte {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idCarte;
	private String nom;
	private String description;
	
	@ManyToMany
	@JoinTable(
			name="asso_cartes_plats",
			joinColumns= {@JoinColumn(name="id_cartes")},
			inverseJoinColumns= {@JoinColumn(name="id_plats")}
	)
	private List<Plat> plats;

	public Carte(String nom, String description, List<Plat> plats) {
		this.nom = nom;
		this.description = description;
		this.plats = plats;
	}
	
	

}
