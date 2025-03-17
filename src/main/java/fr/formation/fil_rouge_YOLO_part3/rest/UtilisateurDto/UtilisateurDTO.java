package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Role;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import jakarta.validation.constraints.Email;
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
    
    @Email
    private String email;
    private Role roleDto;
    private Integer idRestaurant;
    private String nomRestaurant;
    
    public UtilisateurDTO(Utilisateur utilisateur) {
        this.idUtilisateur = utilisateur.getIdUtilisateur();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.telephone = utilisateur.getTelephone();
        this.email = utilisateur.getEmail();
        this.roleDto = utilisateur.getRole();
        this.idRestaurant = utilisateur.getRestaurant().getIdRestaurant();
        this.nomRestaurant = utilisateur.getRestaurant().getNom();
	}

       
}

