package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.repository.UtilisateurRepository;
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurLoggedDTO;
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
		UtilisateurDTO userDto = new UtilisateurDTO(user);
		UtilisateurLoggedDTO userLoggedDto = new UtilisateurLoggedDTO();
		userLoggedDto.setIdUtilisateur(userDto.getIdUtilisateur());
		userLoggedDto.setPrenom(userDto.getPrenom());
		userLoggedDto.setIdRestaurant(userDto.getIdRestaurant());
		
		String jwtToken = jwtService.generateToken(user);
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setToken(jwtToken);
		authResponse.setUser(userLoggedDto);
		return authResponse;
	}
}
