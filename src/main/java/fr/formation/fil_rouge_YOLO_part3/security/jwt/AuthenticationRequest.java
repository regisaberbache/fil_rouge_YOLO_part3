package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "login")
public class AuthenticationRequest {
	private String login;
    private String password;
}
