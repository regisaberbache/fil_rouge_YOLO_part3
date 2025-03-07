package fr.formation.fil_rouge_YOLO_part3.rest.RestaurantDto;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Carte;
import fr.formation.fil_rouge_YOLO_part3.entity.Horaire;
import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
	private Integer idRestaurantDto;
	
	private String nomDto;
	private String adresseDto;
	private String emailDto;
	private String url_imageDto;
	private Carte carteDto;
	private List<Horaire> horairesDto;
	
	public RestaurantDTO(Restaurant restaurant) {
		this.idRestaurantDto = restaurant.getIdRestaurant();
		this.nomDto = restaurant.getNom();
		this.adresseDto = restaurant.getAdresse();
		this.emailDto = restaurant.getEmail();
		this.carteDto = restaurant.getCarte();
		this.horairesDto = restaurant.getHoraires();
	}
	
	public Restaurant toEntity() {
		Restaurant restaurant = new Restaurant();
		restaurant.setIdRestaurant(idRestaurantDto);
		restaurant.setNom(nomDto);
		restaurant.setAdresse(adresseDto);
		restaurant.setEmail(emailDto);
		restaurant.setUrl_image(url_imageDto);
		restaurant.setCarte(carteDto);
		restaurant.setHoraires(horairesDto);
		return restaurant;
	}
	
}
