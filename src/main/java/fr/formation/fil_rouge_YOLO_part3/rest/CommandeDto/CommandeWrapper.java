package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.repository.ReservationRepository;

@Component
public class CommandeWrapper {
    
	private final ReservationRepository reservationRepository;

	@Autowired
	public CommandeWrapper(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());
        dto.setLignes(commande.getLignes());
        dto.setIdReservation(commande.getIdReservation());
        Integer idTableRestaurant = reservationRepository.findIdTableRestaurantById(commande.getIdReservation());
        dto.setIdTableRestaurant(idTableRestaurant);
        return dto;
    }

    public Commande toEntity(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setIdCommande(dto.getIdCommande());
        commande.setStatut(dto.getStatut());
        commande.setLignes(dto.getLignes());
        return commande;
    }
}

