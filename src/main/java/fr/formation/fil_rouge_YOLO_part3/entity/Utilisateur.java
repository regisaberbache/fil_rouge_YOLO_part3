package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "utilisateurs")
public class Utilisateur implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String login; 
	private String password;

	@OneToOne
	@JoinColumn(name = "id_roles")
	private Role role;


	@OneToOne
	@JoinColumn(name = "id_restaurants")
	private Restaurant restaurant;

	public Utilisateur(String nom, String prenom, String telephone, String email, String login, String password,
			Role role, Restaurant restaurant) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return nom + ", prenom=" + prenom + ", telephone="
				+ telephone + ", email=" + email + ", login=" + login + ", password=" + password + ", role=" + role.getLibelle()
				+ ", restaurant=" + restaurant.getNom() + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.getLibelle()));
	}

	@Override
	public String getUsername() {
		return login;
	}
	
	
	
	
}
