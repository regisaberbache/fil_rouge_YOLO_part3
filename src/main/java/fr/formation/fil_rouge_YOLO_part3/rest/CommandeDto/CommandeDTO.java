package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.LigneCommande;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
	
	private Integer idCommande;
	private String statut;
	private Integer idReservation;
	private List<LigneCommande> lignes;

}
