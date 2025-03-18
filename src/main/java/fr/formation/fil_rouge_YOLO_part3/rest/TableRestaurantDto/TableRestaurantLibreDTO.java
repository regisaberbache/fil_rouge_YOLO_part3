package fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto;

import java.util.List;
import java.util.stream.Collectors;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableRestaurantLibreDTO {
	private Integer idRestaurant;
	private Integer idTableRestaurant;
	private Integer numeroTable;
	private Integer nbPlaces;
		
	public TableRestaurantLibreDTO(TableRestaurant tableRestaurant) {
		this.idTableRestaurant = tableRestaurant.getIdTableRestaurant();
		this.nbPlaces = tableRestaurant.getNbPlaces();
		this.numeroTable = tableRestaurant.getNumeroTable();
		if (tableRestaurant.getRestaurant() != null || tableRestaurant.getRestaurant().getIdRestaurant() != null) {
            this.idRestaurant = tableRestaurant.getRestaurant().getIdRestaurant();
        } else {
            this.idRestaurant = null;
        }
	}
	
	public TableRestaurantLibreDTO(TableRestaurant tableRestaurant, List<ReservationDTO> reservationsFiltrees) {
	    this.idTableRestaurant = tableRestaurant.getIdTableRestaurant();
	    this.nbPlaces = tableRestaurant.getNbPlaces();
	    this.numeroTable = tableRestaurant.getNumeroTable();
	    if (tableRestaurant.getRestaurant() != null && tableRestaurant.getRestaurant().getIdRestaurant() != null) {
	        this.idRestaurant = tableRestaurant.getRestaurant().getIdRestaurant();
	    } else {
	        this.idRestaurant = null;
	    }
	}
 		
	public TableRestaurant toEntity() {
		TableRestaurant tableRestaurant = new TableRestaurant();
		tableRestaurant.setIdTableRestaurant(idTableRestaurant);
		tableRestaurant.setNbPlaces(nbPlaces);
		tableRestaurant.setNumeroTable(numeroTable);
		return tableRestaurant;
	}
}