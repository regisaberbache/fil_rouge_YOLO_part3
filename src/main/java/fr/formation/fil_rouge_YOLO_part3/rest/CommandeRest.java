package fr.formation.fil_rouge_YOLO_part3.rest;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.exceptions.CommandeServiceException;
import fr.formation.fil_rouge_YOLO_part3.exceptions.PlatServiceException;
import fr.formation.fil_rouge_YOLO_part3.exceptions.ReservationServiceException;
import fr.formation.fil_rouge_YOLO_part3.exceptions.TableRestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto.CommandeDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.CommandeDto.CommandeMapper;
import fr.formation.fil_rouge_YOLO_part3.service.CommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.LigneCommandeService;
import fr.formation.fil_rouge_YOLO_part3.service.PlatService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping("/commandes")
public class CommandeRest {
	@Autowired
	CommandeService commandeService;
	
	@Autowired
	LigneCommandeService ligneCommandeService;
	
	@Autowired
	PlatService platService;
	
	@Autowired
	CommandeMapper  commandeMapper;
	
	@Operation(summary = "Liste les commandes",
			description = "")
	@GetMapping
	public ResponseEntity<List<CommandeDTO>> getAll() throws TableRestaurantServiceException {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : commandeService.getAllCommandes()) {
			lst.add( commandeMapper.toDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{idRestau}/statut/{statut}")
	public ResponseEntity<List<CommandeDTO>> getAllCommandesByStatutAndIdRestaurant(@PathVariable("idRestau") Integer idRestau,
																	 @PathVariable("statut") String statut) throws CommandeServiceException, TableRestaurantServiceException {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : commandeService.getAllCommandesByStatutAndIdRestaurant(idRestau,statut)) {
			lst.add(commandeMapper.toDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("statut/{statut}")
	public ResponseEntity<List<CommandeDTO>> getAllCommandesByStatut(@PathVariable("statut") String statut) throws CommandeServiceException, TableRestaurantServiceException {
		List<CommandeDTO> lst = new ArrayList<>();
		for (Commande commande : commandeService.getAllCommandesByStatut(statut)) {
			lst.add(commandeMapper.toDTO(commande));
		}
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("{statut}/{id}")
	public ResponseEntity<Object> getCommandeByStatutAndId(@PathVariable("statut") String statut, @PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		if(commande.getStatut().equalsIgnoreCase(statut)) {			
			return ResponseEntity.ok(commandeMapper.toDTO(commande));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée pour cet id et ce statut");
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
		}
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	

	// Crée commande vide avec idReservation et idTableRestaurant. Statut "brouillon" par défaut.
	/* Exemple de JSON dans le body du POST :
	{
	  "statut": "en_cours",
	  "reservationDto": {
	    "idReservation": 2,
	    "nbPersonne": 4,
	    "statut": "arrivee",
	    "horaireReservation": "2025-04-09T12:30:00",
	    "utilisateur": {
	      "idUtilisateur": 3,
	      "nom": "Dupont",
	      "prenom": "Jean",
	      "telephone": "0612345678",
	      "email": "jean.dupont@email.com",
	      "roleDto": {
	        "idRole": 1,
	        "libelle": "Client"
	      },
	      "idRestaurant": 1,
	      "nomRestaurant": "Le Bistro Parisien"
	    },
	    "idTableRestaurant": 4
	  },
	  "idTableRestaurant": 4,
	  "nomClient": "Jean Dupont",
	  "nbPersonnes": 4,
	  "numeroTable": 12
	}
	*/
	@PostMapping
	public ResponseEntity<CommandeDTO> create(@RequestBody CommandeDTO commandeDto) {
		commandeDto.setStatut("brouillon");
		try {
			Commande commande = commandeService.createCommande(commandeMapper.toEntity(commandeDto));
			commandeDto.setIdCommande(commande.getIdCommande());
		} catch (ReservationServiceException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(commandeDto);
	}
	
	
	@PutMapping("/{id}/ajouterplat")
	public ResponseEntity<Integer> ajoutPlat(@PathVariable("id") Integer idCommande, @RequestParam String plat) throws TableRestaurantServiceException {
		if (plat == null || plat.isBlank()) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Integer idPlat;
		try {
			idPlat = Integer.parseInt(plat);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Integer qtePlatFinale = null;
		
		try {
			qtePlatFinale = commandeService.ajouterPlatACommande(idCommande, idPlat);
		} catch (CommandeServiceException e) {
			e.printStackTrace();
		} catch (PlatServiceException e) {
			e.printStackTrace();
		}
		System.out.println("qte plat modifiee => " + qtePlatFinale);
	    return ResponseEntity.ok(qtePlatFinale);
	}

	
	
	@PutMapping("/{id}/retirerplat")
	public ResponseEntity<Integer> retraitPlat(@PathVariable("id") Integer idCommande, @RequestParam String plat) throws TableRestaurantServiceException {
		if (plat == null || plat.isBlank()) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Integer idPlat;
		try {
			idPlat = Integer.parseInt(plat);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Integer qtePlatFinale = null;
		
		try {
			qtePlatFinale = commandeService.retirerPlatACommande(idCommande, idPlat);
		} catch (CommandeServiceException e) {
			e.printStackTrace();
		} catch (PlatServiceException e) {
			e.printStackTrace();
		}
	    return ResponseEntity.ok(qtePlatFinale);
	}
	
	
		
	@PutMapping("/{id}/fermer")
	public ResponseEntity<Object> updatePassee(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
	        commande.setStatut("passee");
	        commandeService.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	
	@PutMapping("/{id}/prete")
	public ResponseEntity<Object> updatePrete(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
	        commande.setStatut("prete");
	        commandeService.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	
	@PutMapping("/{id}/servie")
	public ResponseEntity<Object> updateServie(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
	        commande.setStatut("servie");
	        commandeService.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		System.out.println("commande " + commande.getIdCommande() + " passé en : " + commande.getStatut());
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
	
	@PutMapping("/{id}/payer")
	public ResponseEntity<Object> updatePayee(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
	        commande.setStatut("payee");
	        commandeService.updateCommande(commande);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws CommandeServiceException, TableRestaurantServiceException {
		Commande commande;
		try {
			commande = commandeService.getCommandeById(id);
		} catch (CommandeServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		commandeService.deleteCommande(commande);
		return ResponseEntity.ok(commandeMapper.toDTO(commande));
	}
}
