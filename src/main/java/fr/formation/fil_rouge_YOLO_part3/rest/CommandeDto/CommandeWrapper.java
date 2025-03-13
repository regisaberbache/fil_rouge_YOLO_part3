package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandeWrapper {
    

    private final CommandeService commandeService;

    @Autowired
    public CommandeWrapper(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setIdCommande(commande.getIdCommande());
        dto.setStatut(commande.getStatut());
        dto.setLignes(commande.getLignes());
        dto.setIdReservation(commandeService.getIdReservationByIdCommande(commande.getIdCommande()));
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

