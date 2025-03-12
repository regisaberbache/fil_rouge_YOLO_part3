package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurDTO;
import fr.formation.fil_rouge_YOLO_part3.service.UtilisateurService;
import fr.formation.fil_rouge_YOLO_part3.service.UtilisateurServiceException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurRest {
	@Autowired
	UtilisateurService service;
	
	@GetMapping
	public ResponseEntity<List<UtilisateurDTO>> getAll() {
		List<UtilisateurDTO> lst = new ArrayList<>();
		for (Utilisateur utilisateur : service.getAllUtilisateurs()) {
			lst.add(new UtilisateurDTO(utilisateur));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
		Utilisateur utilisateur;
		try {
			utilisateur = service.getUtilisateurById(id);
		} catch (UtilisateurServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(new UtilisateurDTO(utilisateur));
	}
	
	@PostMapping
	public ResponseEntity<UtilisateurDTO> create(@Valid @RequestBody UtilisateurDTO utilisateurDto) {
		// TODO Gérer les exceptions
		service.createUtilisateur(utilisateurDto.toEntity());
		return ResponseEntity.ok(utilisateurDto);
	}
	
	@PutMapping
	public ResponseEntity<UtilisateurDTO> update(@Valid @RequestBody UtilisateurDTO utilisateurDto) {
		// TODO Gérer les exceptions
		service.updateUtilisateur(utilisateurDto.toEntity());
		return ResponseEntity.ok(utilisateurDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws UtilisateurServiceException {
		Utilisateur utilisateur;
		try {
			utilisateur = service.getUtilisateurById(id);
		} catch (UtilisateurServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		service.deleteUtilisateur(utilisateur);
		return ResponseEntity.ok(new UtilisateurDTO(utilisateur));
	}
	
	
}
