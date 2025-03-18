package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.rest.LigneCommandeDto.LigneCommandeDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationServiceException;

@Component
public class CommandeMapper {
    
	private final ReservationService reservationService;

	@Autowired
	public CommandeMapper(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());
        
        List<LigneCommandeDTO> lignesDto = new ArrayList<>();
        for(LigneCommande ligne : commande.getLignes()) {
        	lignesDto.add(new LigneCommandeDTO(ligne));
        }
        dto.setLignes(lignesDto);
        
        Integer idTableRestaurant = reservationService.getIdTableRestaurantById(commande.getReservation().getIdReservation());
        dto.setIdTableRestaurant(idTableRestaurant);
        dto.setReservationDto(new ReservationDTO(commande.getReservation()));
        return dto;
    }

    public Commande toEntity(CommandeDTO dto) throws ReservationServiceException {
        Commande commande = new Commande();
        commande.setIdCommande(dto.getIdCommande());
        commande.setStatut(dto.getStatut());
        //commande.setLignes(dto.getLignes());
        Reservation reservation = reservationService.getReservationById(dto.getReservationDto().getIdReservation());
        commande.setReservation(reservation);
        return commande;
    }
}

