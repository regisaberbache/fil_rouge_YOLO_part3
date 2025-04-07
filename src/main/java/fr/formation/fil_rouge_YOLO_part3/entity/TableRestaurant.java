package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tables_restaurant")
public class TableRestaurant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idTableRestaurant;
	
	@Column(name="nb_places")
	private Integer nbPlaces;
	
	@Column(name="numero_table")
	private Integer numeroTable;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurants")
	private Restaurant restaurant;
	
	@OneToMany
	@JoinColumn(name="id_tables_restaurant")
	private List<Reservation> reservations;
	
}
