package fr.formation.fil_rouge_YOLO_part3.rest.reservationDto;

import java.time.LocalDateTime;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
	private Integer idReservation;
	private Integer nbPersonne;
	private String statut;
	private LocalDateTime horaireReservation;
	private UtilisateurDTO utilisateur;

	
	public ReservationDTO(Reservation reservation) {
		UtilisateurWrapper utilisateurWrapper = new UtilisateurWrapper();
		
		this.idReservation = reservation.getIdReservation();
		this.nbPersonne = reservation.getNbPersonne();
		this.statut = reservation.getStatut();
		this.horaireReservation = reservation.getHoraireReservation();
		this.utilisateur = new UtilisateurDTO(reservation.getUtilisateur());
		//this.utilisateur = utilisateurWrapper.toDto(reservation.getUtilisateur());
	}
	
	public Reservation toEntity() {
		return Reservation.builder()
				.idReservation(idReservation)
				.nbPersonne(nbPersonne)
				.statut(statut)
				.horaireReservation(horaireReservation)
				.build();
	}
}
