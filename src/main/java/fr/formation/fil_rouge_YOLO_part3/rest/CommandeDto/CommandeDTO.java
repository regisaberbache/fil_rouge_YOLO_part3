package fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.rest.LigneCommandeDto.LigneCommandeDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
	
	private Integer idCommande;
	private String statut;
	private ReservationDTO reservationDto;
	private Integer idTableRestaurant;
	private Integer numeroTable;
	private List<LigneCommandeDTO> lignes;
	

}
