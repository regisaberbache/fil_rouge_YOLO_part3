package fr.formation.fil_rouge_YOLO_part3.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.security.jwt.AuthenticationRequest;
import fr.formation.fil_rouge_YOLO_part3.security.jwt.AuthenticationResponse;
import fr.formation.fil_rouge_YOLO_part3.security.jwt.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
		
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}
