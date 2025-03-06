package fr.formation.fil_rouge_YOLO_part3.entity;

import jakarta.persistence.Column;
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
@Table(name = "roles")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idRole;
	private String libelle;
	
	public Role(String libelle) {
		this.libelle = libelle;
	}

}
