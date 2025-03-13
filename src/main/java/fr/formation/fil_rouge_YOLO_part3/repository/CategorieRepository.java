package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.formation.fil_rouge_YOLO_part3.entity.Categorie;

@RepositoryRestResource(collectionResourceRel="categories", path="categories")
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
