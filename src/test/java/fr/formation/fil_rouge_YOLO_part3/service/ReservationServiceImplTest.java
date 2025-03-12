package fr.formation.fil_rouge_YOLO_part3.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;

@SpringBootTest
class ReservationServiceImplTest implements CommandLineRunner {
	@Autowired
	ReservationService reservationService;
	@Autowired
	UtilisateurService utilisateurService;
	
	// à tester :
	// nbPersonnes > 0 et <= 8
	// nbPersonnes > 8 => alert "Veuillez contacter le restaurant pour une réservation de plus de 8 personnes"
	// date doit être une date d'ouverture
	// horaire doit être un horaire d'ouverture
	// date est bien today ou après
	// si date today, horaire doit être après now
	
	@BeforeEach
    void setUp() {

    }

	@Test
	void CreateReservation_insere_une_Reservation() throws UtilisateurServiceException {
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(1);
		System.out.println(utilisateur.getNom());
		
		/*
		 * Reservation resa = reservationService.createReservation(Reservation.builder()
		 * .nbPersonne(4) .statut("En attente")
		 * .horaireReservation(LocalDateTime.of(2015, 03, 20, 12, 30))
		 * .utilisateur(utilisateur) .build());
		 * System.out.println(resa.getNbPersonne());
		 */
		//assertTrue(resa != null);
		
		fail("Not yet implemented");
	}

	@Test
	void testGetAllReservations() {
		fail("Not yet implemented");
	}

	@Test
	void testGetReservationById() throws ReservationServiceException {
		Reservation resa = reservationService.getReservationById(2);
		System.out.println(resa.getStatut());
		assertTrue(resa != null);
	}

	@Test
	void testUpdateReservation() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteReservation() {
		fail("Not yet implemented");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	}

}
