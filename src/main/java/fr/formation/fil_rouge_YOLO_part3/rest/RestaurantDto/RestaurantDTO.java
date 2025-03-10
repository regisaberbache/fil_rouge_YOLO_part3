package fr.formation.fil_rouge_YOLO_part3.rest.RestaurantDto;

import java.util.List;
import java.util.stream.Collectors;

import fr.formation.fil_rouge_YOLO_part3.entity.Carte;
import fr.formation.fil_rouge_YOLO_part3.entity.Horaire;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
	private Integer idRestaurantDto;
	
	private String nom;
	private String adresse;
	private String email;
	private String url_image;
	private Carte carte;
	private List<Horaire> horaires;
	private List<TableRestaurantDTO> tablesRestaurant;

	public RestaurantDTO(Restaurant restaurant) {
		this.idRestaurantDto = restaurant.getIdRestaurant();
		this.nom = restaurant.getNom();
		this.adresse = restaurant.getAdresse();
		this.url_image = restaurant.getUrl_image();
		this.email = restaurant.getEmail();
		this.carte = restaurant.getCarte();
		this.horaires = restaurant.getHoraires();
		this.tablesRestaurant = restaurant.getTablesRestaurant().stream()
		        .map(table -> new TableRestaurantDTO(table))
		        .collect(Collectors.toList());
	}
	
	public Restaurant toEntity() {
		Restaurant restaurant = new Restaurant();
		restaurant.setIdRestaurant(idRestaurantDto);
		restaurant.setNom(nom);
		restaurant.setAdresse(adresse);
		restaurant.setEmail(email);
		restaurant.setUrl_image(url_image);
		restaurant.setCarte(carte);
		restaurant.setHoraires(horaires);
		return restaurant;
	}
	
}
