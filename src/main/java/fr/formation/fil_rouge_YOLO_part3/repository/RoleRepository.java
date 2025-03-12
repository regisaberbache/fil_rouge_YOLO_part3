package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.formation.fil_rouge_YOLO_part3.entity.Role;

@RepositoryRestResource(collectionResourceRel="roles", path="roles")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
