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
import fr.formation.fil_rouge_YOLO_part3.rest.UtilisateurDto.UtilisateurWrapper;
import fr.formation.fil_rouge_YOLO_part3.service.RestaurantServiceException;
import fr.formation.fil_rouge_YOLO_part3.service.UtilisateurService;
import fr.formation.fil_rouge_YOLO_part3.service.UtilisateurServiceException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurRest {

    @Autowired
    private UtilisateurService service;

    @Autowired
    private UtilisateurWrapper utilisateurWrapper;

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAll() {
        List<UtilisateurDTO> lst = new ArrayList<>();
        for (Utilisateur utilisateur : service.getAllUtilisateurs()) {
            lst.add(utilisateurWrapper.wrap(utilisateur));
        }
        return ResponseEntity.ok(lst);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        try {
            Utilisateur utilisateur = service.getUtilisateurById(id);
            return ResponseEntity.ok(utilisateurWrapper.wrap(utilisateur));
        } catch (UtilisateurServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("identifiant non trouvé");
        }
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> create(@Valid @RequestBody UtilisateurDTO utilisateurDto) 
            throws RestaurantServiceException {
        Utilisateur utilisateur = service.createUtilisateur(utilisateurWrapper.unwrap(utilisateurDto));
		return ResponseEntity.ok(utilisateurWrapper.wrap(utilisateur));
    }

    @PutMapping("{id}")
    public ResponseEntity<UtilisateurDTO> update(@Valid @RequestBody UtilisateurDTO utilisateurDto, 
            @PathVariable("id") Integer id) throws RestaurantServiceException {
        try {
            Utilisateur existingUtilisateur = service.getUtilisateurById(id);
            Utilisateur updatedUtilisateur = utilisateurWrapper.updateEntity(existingUtilisateur, utilisateurDto);
            service.updateUtilisateur(updatedUtilisateur);
            return ResponseEntity.ok(utilisateurWrapper.wrap(updatedUtilisateur));
        } catch (UtilisateurServiceException e) {
            // TODO: Add proper exception handling as per your requirements
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws UtilisateurServiceException {
        try {
            Utilisateur utilisateur = service.getUtilisateurById(id);
            service.deleteUtilisateur(utilisateur);
            return ResponseEntity.ok(utilisateurWrapper.wrap(utilisateur));
        } catch (UtilisateurServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}