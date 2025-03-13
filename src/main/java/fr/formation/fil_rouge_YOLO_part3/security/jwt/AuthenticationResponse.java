package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponse {
	private String token;
}
