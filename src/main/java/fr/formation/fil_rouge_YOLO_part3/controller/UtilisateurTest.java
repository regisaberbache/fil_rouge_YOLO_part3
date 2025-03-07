package fr.formation.fil_rouge_YOLO_part3.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.fil_rouge_YOLO_part3.FilRougeYoloPart3Application;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;
import fr.formation.fil_rouge_YOLO_part3.service.UtilisateurService;

@SpringBootApplication
public class UtilisateurTest implements CommandLineRunner {
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(FilRougeYoloPart3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(1);
		System.out.println(utilisateur.getNom());
		
		Reservation resa = reservationService.getReservationById(2);
		
		/*
		 * Reservation resa = reservationService.createReservation(Reservation.builder()
		 * .nbPersonne(4) .statut("En attente")
		 * .horaireReservation(LocalDateTime.of(2015, 03, 20, 12, 30))
		 * .utilisateur(utilisateur) .build());
		 * System.out.println(resa.getNbPersonne());
		 */
		
		System.out.println(resa.getStatut());
		
			
	}
	
}
