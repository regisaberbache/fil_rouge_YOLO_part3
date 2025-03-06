package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	private String nom;
	private String adresse;
	private String email;
	private String url_image;
	
	@OneToOne
	@JoinColumn(name = "id_cartes")
	private Carte carte;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_restaurants")
	private List<TableRestaurant> tablesRestaurant;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_restaurants")
	private List<Horaire> horaires;

	public Restaurant(String nom, String adresse, String email, String url_image, Carte carte) {
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.url_image = url_image;
		this.carte = carte;
	}


	
	
}
