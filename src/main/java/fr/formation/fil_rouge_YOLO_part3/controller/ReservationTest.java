package fr.formation.fil_rouge_YOLO_part3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.fil_rouge_YOLO_part3.FilRougeYoloPart3Application;
import fr.formation.fil_rouge_YOLO_part3.entity.Reservation;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationService;

@SpringBootApplication
public class ReservationTest implements CommandLineRunner {
	@Autowired
	ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(FilRougeYoloPart3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Reservation reservation = reservationService.getReservationById(1);
		
		System.out.println(reservation.getUtilisateur().getNom());
		
		
			
	}
	
}
