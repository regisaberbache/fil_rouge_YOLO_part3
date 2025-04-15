package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import org.springframework.stereotype.Component;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.exceptions.ReservationServiceException;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;

@Component
public class CommandeMapper {
    
	public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());      
        dto.setLignes(commande.getLignes());
        dto.setReservationDto(new ReservationDTO(commande.getReservation()));
        return dto;
    }

    public Commande toEntity(CommandeDTO dto) throws ReservationServiceException {
        Commande commande = new Commande();
        commande.setIdCommande(dto.getIdCommande());
        commande.setStatut(dto.getStatut());
        Reservation reservation = dto.getReservationDto().toEntity();
        commande.setReservation(reservation);
        return commande;
    }
}

