package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
//	private String loginDto;
//	private String passwordDto;
//	private Role roleDto;
//	private Restaurant restaurantDto;
	
	public UtilisateurDTO(Utilisateur utilisateur) {
		this.idUtilisateur = utilisateur.getIdUtilisateur();
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.email = utilisateur.getEmail();
		this.telephone = utilisateur.getTelephone();
//		this.loginDto = utilisateur.getLogin();
//		this.passwordDto = utilisateur.getPassword();
//		this.roleDto = utilisateur.getRole();
//		this.restaurantDto = utilisateur.getRestaurant();
	}
	
	public Utilisateur toEntity() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(idUtilisateur);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
//		utilisateur.setLogin(loginDto);
//		utilisateur.setPassword(passwordDto);
//		utilisateur.setRole(roleDto);
//		utilisateur.setRestaurant(restaurantDto);
		return utilisateur;
	}
	
}
