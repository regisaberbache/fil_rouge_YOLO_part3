package fr.formation.fil_rouge_YOLO_part3.rest.DemandeResaDto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeResaDTO {
	private LocalDate date;
	private LocalTime heure;
	private Integer nbPersonnes;

}

/* JSON :

{
"date": "2025-03-18",
"heure": "14:30:00",
"nbPersonnes": 4
}

*/