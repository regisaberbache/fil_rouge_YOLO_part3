package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "utilisateurs")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String login;
	private String password;

	@OneToOne
	@JoinColumn(name = "id_roles")
	private Role role;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_utilisateurs")
	private List<Reservation> reservations;	
	@OneToOne
	@JoinColumn(name = "id_restaurants")
	private Restaurant restaurant;

	public Utilisateur(String nom, String prenom, String telephone, String email, String login, String password,
			Role role, List<Reservation> reservations, Restaurant restaurant) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.reservations = reservations;
		this.restaurant = restaurant;
	}
	
	
}
