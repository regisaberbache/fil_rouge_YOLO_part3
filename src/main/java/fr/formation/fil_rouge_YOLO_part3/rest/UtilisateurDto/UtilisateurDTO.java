package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Role;
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
    
    @NotBlank(message = "Le nom doit être renseigné")
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
       
}