package fr.formation.fil_rouge_YOLO_part3.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Utiliser la BDD SQLServer YOLOTEST
class ReservationServiceImplTest {
	@Autowired
	ReservationServiceImpl reservationService;

	@BeforeEach
	void setUp() {
	}

	// Récupération de toutes les réservations
	@Test
	void testGetAllReservations() {
		List<Reservation> reservations = reservationService.getAllReservations();
		assertFalse(reservations.isEmpty());
		assertEquals(7, reservations.size());
	}
	
	// Récupération par ID
	@Test
	void testGetReservationById() throws ReservationServiceException {
		Reservation reservation = reservationService.getReservationById(1);
		assertNotNull(reservation);
		assertEquals(1, reservation.getIdTableRestaurant());
	}
	
	 // Gestion d'une erreur si l'ID n'existe pas
	@Test
	void testGetRestaurantById_NotFound() {
		Exception exception = assertThrows(ReservationServiceException.class, () -> {
			reservationService.getReservationById(99);
		});
		assertEquals("Réservation non trouvée", exception.getMessage());
	}
	
    // Création d'une réservation
	@Test
	void testcreateReservation() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(1);
		
		Reservation reservation = new Reservation(1, 2, "confirmée", LocalDateTime.of(2025, 3, 20, 12, 30), utilisateur);
		reservationService.createReservation(reservation);
        
		// Assert (Vérifications)
		List<Reservation> reservations = reservationService.getAllReservations();
		assertEquals(7, reservations.size()); // Vérifie que la réservation a bien été ajoutée
        Reservation savedReservation = reservations.get(7);
        assertEquals(2, savedReservation.getNbPersonne());
        assertEquals("confirmée", savedReservation.getStatut());
        assertEquals(LocalDateTime.of(2025, 3, 20, 12, 30), savedReservation.getHoraireReservation());
        assertEquals(1, savedReservation.getUtilisateur().getIdUtilisateur());
	}
	
}
