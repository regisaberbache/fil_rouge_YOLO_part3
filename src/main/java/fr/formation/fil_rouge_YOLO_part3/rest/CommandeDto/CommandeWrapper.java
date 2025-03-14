package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.repository.ReservationRepository;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationServiceException;

@Component
public class CommandeWrapper {
    
	private final ReservationRepository reservationRepository;
	private final ReservationService reservationService;

	@Autowired
	public CommandeWrapper(ReservationRepository reservationRepository, ReservationService reservationService) {
		this.reservationRepository = reservationRepository;
		this.reservationService = reservationService;
	}

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());
        dto.setLignes(commande.getLignes());
        dto.setIdReservation(commande.getReservation().getIdReservation());
        Integer idTableRestaurant = reservationRepository.findIdTableRestaurantById(commande.getReservation().getIdReservation());
        dto.setIdTableRestaurant(idTableRestaurant);
        return dto;
    }

    public Commande toEntity(CommandeDTO dto) throws ReservationServiceException {
        Commande commande = new Commande();
        commande.setIdCommande(dto.getIdCommande());
        commande.setStatut(dto.getStatut());
        commande.setLignes(dto.getLignes());
        Reservation reservation = reservationService.getReservationById(dto.getIdReservation());
        commande.setReservation(reservation);
        return commande;
    }
}

