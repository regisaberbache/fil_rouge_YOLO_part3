package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.entity.Role;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
	private Integer idUtilisateurDto;
	private String nomDto;
	private String prenomDto;
	private String telephoneDto;
	private String emailDto;
//	private String loginDto;
//	private String passwordDto;
//	private Role roleDto;
//	private Restaurant restaurantDto;
	
	public UtilisateurDTO(Utilisateur utilisateur) {
		this.idUtilisateurDto = utilisateur.getIdUtilisateur();
		this.nomDto = utilisateur.getNom();
		this.prenomDto = utilisateur.getPrenom();
		this.emailDto = utilisateur.getEmail();
		this.telephoneDto = utilisateur.getTelephone();
//		this.loginDto = utilisateur.getLogin();
//		this.passwordDto = utilisateur.getPassword();
//		this.roleDto = utilisateur.getRole();
//		this.restaurantDto = utilisateur.getRestaurant();
	}
	
	public Utilisateur toEntity() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(idUtilisateurDto);
		utilisateur.setNom(nomDto);
		utilisateur.setPrenom(prenomDto);
		utilisateur.setEmail(emailDto);
		utilisateur.setTelephone(telephoneDto);
//		utilisateur.setLogin(loginDto);
//		utilisateur.setPassword(passwordDto);
//		utilisateur.setRole(roleDto);
//		utilisateur.setRestaurant(restaurantDto);
		return utilisateur;
	}
	
}
