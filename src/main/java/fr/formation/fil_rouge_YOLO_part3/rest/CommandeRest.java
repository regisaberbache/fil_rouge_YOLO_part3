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
import fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto.CommandeMapper;
import fr.formation.fil_rouge_YOLO_part3.rest.PlatDto.PlatDTO;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.PlatService;
import fr.formation.fil_rouge_YOLO_part3.service.ReservationServiceException;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/commandes")
public class CommandeRest {
	@Autowired
	CommandeService service;
	
	@Autowired
	PlatService platService;
	
	@Autowired
	CommandeMapper  commandeMapper;
	
	@Operation(summary = "Liste les commandes",
			description = "")
	@GetMapping
	public ResponseEntity<List<CommandeDTO>> getAll() {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : service.getAllCommandes()) {
			lst.add( commandeMapper.toDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{statut}")
	public ResponseEntity<List<CommandeDTO>> getAllCommandesByStatut(@PathVariable("statut") String statut) throws CommandeServiceException {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : service.getAllCommandesByStatut(statut)) {
			lst.add(commandeMapper.toDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{statut}/{id}")
	public ResponseEntity<Object> getCommandeByStatutAndId(@PathVariable("statut") String statut, @PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		if(commande.getStatut().equalsIgnoreCase(statut)) {			
			return ResponseEntity.ok(commandeMapper.toDTO(commande));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée pour cet id et ce statut");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	

	// Crée commande vide avec idReservation et idTableRestaurant. Statut "brouillon" par défaut.
	/* Exemple de JSON dans le body du POST :
	{
	    "": 1,
	    "idTableRestaurant": 1,
	    "lignes": []
	}
	*/
	@PostMapping
	public ResponseEntity<CommandeDTO> create(@RequestBody CommandeDTO commandeDto) {
		commandeDto.setStatut("brouillon");
		try {
			Commande commande = service.createCommande(commandeMapper.toEntity(commandeDto));
			commandeDto.setIdCommande(commande.getIdCommande());
		} catch (ReservationServiceException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(commandeDto);
	}
	
	
	// TODO : ajout plat
	@PutMapping("/{idPlat}/ajouter")
	public ResponseEntity<CommandeDTO> update(@RequestBody CommandeDTO commandeDto, @RequestBody PlatDTO platDto) {
		// TODO Gérer les exceptions
		try {
			service.updateCommande(commandeMapper.toEntity(commandeDto));
		} catch (ReservationServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(commandeDto);
	}
	
		
	@PutMapping("/{id}/fermer")
	public ResponseEntity<Object> updatePassee(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
	        commande.setStatut("passee");
	        service.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	
	@PutMapping("/{id}/prete")
	public ResponseEntity<Object> updatePrete(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
	        commande.setStatut("prete");
	        service.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	
	@PutMapping("/{id}/payer")
	public ResponseEntity<Object> updatePayee(@PathVariable("id") Integer id) throws CommandeServiceException {
		Commande commande;
		try {
			commande = service.getCommandeById(id);
	        commande.setStatut("payee");
	        service.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(commandeMapper.toDTO(commande));
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
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
}
