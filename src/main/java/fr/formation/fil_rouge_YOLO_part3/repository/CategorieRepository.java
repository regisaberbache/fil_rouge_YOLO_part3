package fr.formation.fil_rouge_YOLO_part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.fil_rouge_YOLO_part3.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
