package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurLoggedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponse {
	private String token;
	private UtilisateurLoggedDTO user;
}
