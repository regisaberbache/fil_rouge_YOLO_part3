package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.exceptions.ReservationServiceException;
import fr.formation.fil_rouge_YOLO_part3.exceptions.TableRestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import fr.formation.fil_rouge_YOLO_part3.service.LigneCommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.TableRestaurantService;

@Component
public class CommandeMapper {
    
	private final ReservationService reservationService;
	private final TableRestaurantService tableRestaurantService;

	@Autowired
	public CommandeMapper(ReservationService reservationService, TableRestaurantService tableRestaurantService) {
		this.reservationService = reservationService;
		this.tableRestaurantService = tableRestaurantService;
	}

    public CommandeDTO toDTO(Commande commande) throws TableRestaurantServiceException {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());
                
        dto.setLignes(commande.getLignes());
        
        Integer idTableRestaurant = reservationService.getIdTableRestaurantById(commande.getReservation().getIdReservation());
        dto.setIdTableRestaurant(idTableRestaurant);
        
        Integer numeroTable = tableRestaurantService.getTableRestaurantById(idTableRestaurant).getNumeroTable();
        dto.setNumeroTable(numeroTable);
        
        Integer nbPersonnes = reservationService.getNbPersonneById(commande.getReservation().getIdReservation());
        dto.setNbPersonnes(nbPersonnes);
        
        String nomClient = reservationService.getNomUtilisateurByUtilisateur(commande.getReservation().getUtilisateur().getIdUtilisateur());
        dto.setNomClient(nomClient);
        
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

