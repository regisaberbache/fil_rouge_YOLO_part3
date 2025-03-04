package fr.formation.fil_rouge_YOLO_part3.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tables_restaurant")
public class TableRestaurant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nb_places")
	private Integer nbPlaces;
	
	@Column(name="numero_table")
	private Integer numeroTable;
	
	@OneToMany
	@JoinColumn(name="id_tables_restaurant")
	private List<Reservation> reservations;
	

	public TableRestaurant(Integer nbPlaces, Integer numeroTable, List<Reservation> reservations) {
		this.nbPlaces = nbPlaces;
		this.numeroTable = numeroTable;
		this.reservations = reservations;
	}
	
	

}
