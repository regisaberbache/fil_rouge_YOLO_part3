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
public class TableRestaurantDTO {
	private Integer idTableRestaurant;
	private Integer nbPlaces;
	private Integer numeroTable;
	private List<ReservationDTO> reservations;
	
	public TableRestaurantDTO(TableRestaurant tableRestaurant) {
		this.idTableRestaurant = tableRestaurant.getIdTableRestaurant();
		this.nbPlaces = tableRestaurant.getNbPlaces();
		this.numeroTable = tableRestaurant.getNumeroTable();
		this.reservations = tableRestaurant.getReservations().stream()
		        .map(reservation -> new ReservationDTO(reservation))
		        .collect(Collectors.toList());
	}
	
	public TableRestaurant toEntity() {
		TableRestaurant tableRestaurant = new TableRestaurant();
		tableRestaurant.setIdTableRestaurant(idTableRestaurant);
		tableRestaurant.setNbPlaces(nbPlaces);
		tableRestaurant.setNumeroTable(numeroTable);
		return tableRestaurant;
	}
	
}
