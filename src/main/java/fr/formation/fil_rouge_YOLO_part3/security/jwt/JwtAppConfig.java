package fr.formation.fil_rouge_YOLO_part3.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.fil_rouge_YOLO_part3.repository.UtilisateurRepository;


@Configuration
public class JwtAppConfig {

	/**
	 * Authentification de l'utilisateur depuis la base de données
	 */
	@Autowired
	private UtilisateurRepository uRepository;

	@Bean
	UserDetailsService userDetailsService() {
		return username -> uRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
