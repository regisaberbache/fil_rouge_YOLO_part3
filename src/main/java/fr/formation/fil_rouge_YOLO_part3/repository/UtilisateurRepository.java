package fr.formation.fil_rouge_YOLO_part3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.fil_rouge_YOLO_part3.entity.Utilisateur;

//@RepositoryRestResource(collectionResourceRel = "utilisateurs", path = "utilisateur")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Utilisateur findUtilisateurByIdUtilisateur(Integer id);
	@Query("FROM Utilisateur u where u.role.libelle = :role")
	Utilisateur trouverUtilisateurParRole(@Param ("role") String role);
	Utilisateur findByIdUtilisateur(Integer idUtilisateur);
	
	Optional<Utilisateur> findByLogin(@Param("login") String login);
	Utilisateur findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
