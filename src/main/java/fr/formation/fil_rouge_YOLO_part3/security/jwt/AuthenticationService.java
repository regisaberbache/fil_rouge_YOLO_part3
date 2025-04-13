package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {
	private UtilisateurRepository uRepository;
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));		
		Utilisateur user = uRepository.findByLogin(request.getLogin()).orElseThrow();
		
		String jwtToken = jwtService.generateToken(user);
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setToken(jwtToken);
		return authResponse;
	}
}
