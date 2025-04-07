package fr.formation.fil_rouge_YOLO_part3.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.fil_rouge_YOLO_part3.entity.Restaurant;
import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Utiliser la BDD SQLServer YOLOTEST
class TableRestaurantServiceImplTest {
	@Autowired
	TableRestaurantService tableRestaurantService;

    @BeforeEach
    void setUp() {
    }
        
    // Création d'une table
    @Test
    void testCreateTableRestaurant() {
        Restaurant restaurant = new Restaurant(); // Créer un restaurant
        restaurant.setIdRestaurant(5); // ID d'un restaurant existant dans la base

        TableRestaurant table = new TableRestaurant(null, 6, 504, restaurant, null);
        tableRestaurantService.createTableRestaurant(table);

        List<TableRestaurant> tables = tableRestaurantService.getAllTableRestaurants();
        assertEquals(23, tables.size()); // nb de tables de YOLOTEST_dataset.sql + 1 ajoutée ici
    }
    
    // Récupération de toutes les tables
    @Test
    void testGetAllTableRestaurants() {
    	List<TableRestaurant> tables = tableRestaurantService.getAllTableRestaurants();
        assertFalse(tables.isEmpty()); // vérifie que la liste tables n'est pas vide
        assertEquals(23, tables.size()); // vérifie la taille de la liste tables
    }
    
    // Récupération par ID
    @Test
    void testGetTableRestauranById() throws TableRestaurantServiceException {
    	TableRestaurant table = tableRestaurantService.getTableRestaurantById(1);
    	assertNotNull(table); // vérifie que l'objet table n'est pas nul
    	assertEquals(6, table.getNbPlaces()); // vérifie que le nombre de places de la table 1 est égal à 4
    }
   
    // Gestion d'une erreur si l'ID n'existe pas
    @Test
    void testGetTableRestaurantById_NotFound() {
        Exception exception = assertThrows(TableRestaurantServiceException.class, () -> {
            tableRestaurantService.getTableRestaurantById(99);
        });  // vérifie que l'appel à getTableRestaurantById(99) lance une exception de type TableRestaurantServiceException.
        // La méthode assertThrows capture cette exception.
        assertEquals("Cet identifiant n'existe pas", exception.getMessage()); //vérifie que le message de l'exception est "Cet identifiant n'existe pas"
    }
    
    // Mise à jour d'une table
    @Test
    void testUpdateTableRestaurant() throws TableRestaurantServiceException {
        TableRestaurant table = tableRestaurantService.getTableRestaurantById(19);
        table.setNbPlaces(8);
	    // Sauvegarde de l'entité après modification
        tableRestaurantService.updateTableRestaurant(table);

        TableRestaurant updatedTable = tableRestaurantService.getTableRestaurantById(19);
        assertEquals(8, updatedTable.getNbPlaces()); // vérifie que le nombre de places de la table est bien mise à jour
    }
    
    // Suppression d'une table
//    @Test
//    void testDeleteTableRestaurant() throws TableRestaurantServiceException {
//        TableRestaurant table = tableRestaurantService.getTableRestaurantById(18);
//        tableRestaurantService.deleteTableRestaurant(table);
//
//        List<TableRestaurant> tables = tableRestaurantService.getAllTableRestaurants();
//        assertEquals(20, tables.size()); // vérifie que la taille de la liste de tables est égale à nb de tables de YOLOTEST_dataset.sql -1 après la suppression
//    }

}
