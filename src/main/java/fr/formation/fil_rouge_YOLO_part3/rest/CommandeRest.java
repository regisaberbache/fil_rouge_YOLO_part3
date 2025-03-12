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

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto.CommandeDTO;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeServiceException;

@RestController
@RequestMapping("/commandes")
public class CommandeRest {
	@Autowired
	CommandeService service;
	
	@GetMapping
	public ResponseEntity<List<CommandeDTO>> getAll() {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : service.getAllCommandes()) {
			lst.add(new CommandeDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("/statut/{statut}")
	public ResponseEntity<List<CommandeDTO>> getAllCommandesByStatut(@PathVariable("statut") String statut) throws CommandeServiceException {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : service.getAllCommandesByStatut(statut)) {
			lst.add(new CommandeDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(new CommandeDTO(commande));
	}
	
	@PostMapping
	public ResponseEntity<CommandeDTO> create(@RequestBody CommandeDTO commandeDto) {
		// TODO Gérer les exceptions
		service.createCommande(commandeDto.toEntity());
		return ResponseEntity.ok(commandeDto);
	}
	
	@PutMapping
	public ResponseEntity<CommandeDTO> update(@RequestBody CommandeDTO commandeDto) {
		// TODO Gérer les exceptions
		service.updateCommande(commandeDto.toEntity());
		return ResponseEntity.ok(commandeDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		service.deleteCommande(commande);
		return ResponseEntity.ok(new CommandeDTO(commande));
	}
}
