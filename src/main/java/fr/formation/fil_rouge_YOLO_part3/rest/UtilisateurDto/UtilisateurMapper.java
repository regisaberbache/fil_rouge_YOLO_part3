package fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantService;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;

@Component
public class UtilisateurMapper {

    private final RestaurantService restaurantService;

    @Autowired
    public UtilisateurMapper(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    
    public UtilisateurMapper() {
        this.restaurantService = null;
    }

	public UtilisateurDTO toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        UtilisateurDTO dto = new UtilisateurDTO();
        
        dto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setEmail(utilisateur.getEmail());
        dto.setTelephone(utilisateur.getTelephone());
        if (utilisateur.getRole() != null) {
        	dto.setRoleDto(utilisateur.getRole());
        }
        if (utilisateur.getRestaurant() != null) {
        	dto.setIdRestaurant(utilisateur.getRestaurant().getIdRestaurant());
        	dto.setNomRestaurant(utilisateur.getRestaurant().getNom());
        }
        
        return dto;
    }

    public Utilisateur toEntity(UtilisateurDTO dto) throws RestaurantServiceException {
        if (dto == null) {
            return null;
        }
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(dto.getIdUtilisateur());
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setTelephone(dto.getTelephone());
        if (dto.getRoleDto() != null && !"".equalsIgnoreCase(dto.getRoleDto().getIdRole().toString()) 
            && !"".equalsIgnoreCase(dto.getRoleDto().getLibelle())) {            
            utilisateur.setRole(dto.getRoleDto());            
        }
        
        if (dto.getIdRestaurant() != null && !"".equalsIgnoreCase(dto.getIdRestaurant().toString())) {
            utilisateur.setRestaurant(restaurantService.getById(dto.getIdRestaurant()));
        }
        
        return utilisateur;
    }

    public Utilisateur updateEntity(Utilisateur existingUtilisateur, UtilisateurDTO dto) 
            throws RestaurantServiceException {
        if (existingUtilisateur == null || dto == null) {
            return null;
        }

        existingUtilisateur.setNom(dto.getNom());
        existingUtilisateur.setPrenom(dto.getPrenom());
        existingUtilisateur.setTelephone(dto.getTelephone());
        existingUtilisateur.setEmail(dto.getEmail());
        
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
   
}