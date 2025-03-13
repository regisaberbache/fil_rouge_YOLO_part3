package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;

@Component
public class UtilisateurWrapper {

    private final RestaurantService restaurantService;

    @Autowired
    public UtilisateurWrapper(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Wraps an Utilisateur entity into an UtilisateurDTO
     */
    public UtilisateurDTO wrap(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        return new UtilisateurDTO(utilisateur);
    }

    /**
     * Unwraps an UtilisateurDTO into an Utilisateur entity
     */
    public Utilisateur unwrap(UtilisateurDTO dto) throws RestaurantServiceException {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = dto.toEntity();
        // Set the restaurant using the service
        if (dto.getIdRestaurant() != null && !"".equalsIgnoreCase(dto.getIdRestaurant().toString())) {
            utilisateur.setRestaurant(restaurantService.getById(dto.getIdRestaurant()));
        }
        return utilisateur;
    }

    /**
     * Updates an existing Utilisateur with data from UtilisateurDTO
     */
    public Utilisateur updateEntity(Utilisateur existingUtilisateur, UtilisateurDTO dto) 
            throws RestaurantServiceException {
        if (existingUtilisateur == null || dto == null) {
            return null;
        }

        existingUtilisateur.setNom(dto.getNom());
        existingUtilisateur.setPrenom(dto.getPrenom());
        existingUtilisateur.setTelephone(dto.getTelephone());
        existingUtilisateur.setEmail(dto.getEmail());
        existingUtilisateur.setLogin(dto.getLoginDto());
        
        if (dto.getPasswordDto() != null && !dto.getPasswordDto().isEmpty()) {
            existingUtilisateur.setPassword(dto.getPasswordDto());
        }
        
        if (dto.getRoleDto() != null) {
            existingUtilisateur.setRole(dto.getRoleDto());
        }
        
        if (dto.getIdRestaurant() != null) {
            existingUtilisateur.setRestaurant(
                restaurantService.getById(dto.getIdRestaurant())
            );
        }

        return existingUtilisateur;
    }

    /**
     * Creates a new UtilisateurDTO with default values
     */
    public UtilisateurDTO createDefault() {
        return new UtilisateurDTO();
    }
}