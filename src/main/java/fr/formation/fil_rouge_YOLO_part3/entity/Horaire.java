package fr.formation.fil_rouge_YOLO_part3.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "horaires")
public class Horaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String jour;
	private LocalDateTime ouverture;
	private LocalDateTime fermeture;
	
	public Horaire(String jour, LocalDateTime ouverture, LocalDateTime fermeture) {
		this.jour = jour;
		this.ouverture = ouverture;
		this.fermeture = fermeture;
	}
	
	

}
