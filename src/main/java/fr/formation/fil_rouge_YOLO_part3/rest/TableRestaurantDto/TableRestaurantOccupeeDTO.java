package fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto;

import java.util.List;
import java.util.stream.Collectors;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto.CommandeDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableRestaurantOccupeeDTO {
	private Integer idTableRestaurant;
	private Integer nbPlaces;
	private Integer numeroTable;
	private Integer idRestaurant;
	private Integer idCommande;
	private CommandeDTO commandeDto;
	private List<ReservationDTO> reservations;
		
	public TableRestaurantOccupeeDTO(TableRestaurant tableRestaurant) {
		this.idTableRestaurant = tableRestaurant.getIdTableRestaurant();
		this.nbPlaces = tableRestaurant.getNbPlaces();
		this.numeroTable = tableRestaurant.getNumeroTable();
		if (tableRestaurant.getRestaurant() != null || tableRestaurant.getRestaurant().getIdRestaurant() != null) {
            this.idRestaurant = tableRestaurant.getRestaurant().getIdRestaurant();
        } else {
            this.idRestaurant = null;
        }
		this.reservations = tableRestaurant.getReservations().stream()
		        .map(reservation -> new ReservationDTO(reservation))
		        .collect(Collectors.toList());
	}
	
	public TableRestaurantOccupeeDTO(TableRestaurant tableRestaurant, List<ReservationDTO> reservationsFiltrees) {
	    this.idTableRestaurant = tableRestaurant.getIdTableRestaurant();
	    this.nbPlaces = tableRestaurant.getNbPlaces();
	    this.numeroTable = tableRestaurant.getNumeroTable();
	    if (tableRestaurant.getRestaurant() != null && tableRestaurant.getRestaurant().getIdRestaurant() != null) {
	        this.idRestaurant = tableRestaurant.getRestaurant().getIdRestaurant();
	    } else {
	        this.idRestaurant = null;
	    }
	    this.reservations = reservationsFiltrees;
	}
 		
	public TableRestaurant toEntity() {
		TableRestaurant tableRestaurant = new TableRestaurant();
		tableRestaurant.setIdTableRestaurant(idTableRestaurant);
		tableRestaurant.setNbPlaces(nbPlaces);
		tableRestaurant.setNumeroTable(numeroTable);
		return tableRestaurant;
	}
}