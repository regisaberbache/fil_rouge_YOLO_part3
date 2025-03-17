package fr.formation.fil_rouge_YOLO_part3.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservations")
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idReservation;
	
	@Column(name = "id_tables_restaurant")
    private Integer idTableRestaurant;
	
//	@ManyToOne
//	@JoinColumn(name="id_tables_restaurant")
//	private TableRestaurant tableRestaurant;
	
	@Column(name="nombre_personne")
	private Integer nbPersonne;
 
	@Column(name = "statut")
	private String statut;
 
	@Column(name = "horaire_reservation")
	private LocalDateTime horaireReservation;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_utilisateurs")
	private Utilisateur utilisateur;
	
	public Reservation(Integer nbPersonne, String statut, LocalDateTime horaireReservation) {
		this.nbPersonne = nbPersonne;
		this.statut = statut;
		this.horaireReservation = horaireReservation;
	}

	@Override
	public String toString() {
		return "Reservation idReservation=" + idReservation + ", nbPersonne=" + nbPersonne + ", statut=" + statut
				+ ", horaireReservation=" + horaireReservation + ", " + utilisateur.getNom();
	}
	
	
}
