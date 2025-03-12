package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Role;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceImpl;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
	
	private Integer idUtilisateur;
	
	@NotBlank(message = "Le nom doit être renseigné") // validateur pour de la validation frontale
	private String nom;
	private String prenom;
	private String telephone;
	
	@Email
	private String email;
	private String loginDto;
	private String passwordDto;
	private Role roleDto;
	private Integer idRestaurant;
	private String nomRestaurant;
	
	public UtilisateurDTO(Utilisateur utilisateur) {
		this.idUtilisateur = utilisateur.getIdUtilisateur();
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.email = utilisateur.getEmail();
		this.telephone = utilisateur.getTelephone();
		this.loginDto = utilisateur.getLogin();
		this.passwordDto = utilisateur.getPassword();
		if(utilisateur.getRole() != null) {
			this.roleDto = utilisateur.getRole();
		}
		if(utilisateur.getRestaurant() != null) {
			this.idRestaurant = utilisateur.getRestaurant().getIdRestaurant();
			this.nomRestaurant = utilisateur.getRestaurant().getNom();
		}
	}
	
	public Utilisateur toEntity() throws RestaurantServiceException {	
		RestaurantService restaurantService = new RestaurantServiceImpl();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(idUtilisateur);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setLogin(loginDto);
		utilisateur.setPassword(passwordDto);
		if(roleDto != null || !"".equalsIgnoreCase(roleDto.getIdRole().toString()) || !"".equalsIgnoreCase(roleDto.getLibelle())) {			
			utilisateur.setRole(roleDto);			
		}
		if(idRestaurant != null) {
			utilisateur.setRestaurant(restaurantService.getById(idRestaurant));
		}
		return utilisateur;
	}
	
}
