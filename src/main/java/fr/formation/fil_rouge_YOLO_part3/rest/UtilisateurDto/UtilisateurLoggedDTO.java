package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurLoggedDTO {
    
	private Integer idUtilisateur;
    private String prenom;
    private Integer idRestaurant;
    
    public UtilisateurLoggedDTO(Utilisateur utilisateur) {
        this.idUtilisateur = utilisateur.getIdUtilisateur();
        this.prenom = utilisateur.getPrenom();
        this.idRestaurant = utilisateur.getRestaurant().getIdRestaurant();
	}      
}