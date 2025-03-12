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
@Table(name = "plats")
public class Plat {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idPlat;
	private String nom;
	private Double prix;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_categories")
	private Categorie categorie;

	public Plat(String nom, Double prix, String description, Categorie categorie) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
	}
	
	

}
