package fr.formation.fil_rouge_YOLO_part3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.Commande;
import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.TableRestaurantRepository;
import fr.formation.fil_rouge_YOLO_part3.rest.TableRestaurantDto.TableRestaurantDTO;
import fr.formation.fil_rouge_YOLO_part3.rest.reservationDto.ReservationDTO;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {
	@Autowired
	TableRestaurantRepository repo;
	@Autowired
	CommandeService commandeService;

	@Override
	public void createTableRestaurant(TableRestaurant tableRestaurant) {
		repo.save(tableRestaurant);
	}
 
	@Override
	public List<TableRestaurant> getAllTableRestaurants() {
		return repo.findAll();
	}

	@Override
	public TableRestaurant getTableRestaurantById(Integer id) throws TableRestaurantServiceException {
		Optional<TableRestaurant> tableRestaurant = repo.findById(id);
		if(tableRestaurant.isPresent()) {
			return tableRestaurant.get();
		}
		else {
			throw new TableRestaurantServiceException("Cet identifiant n'existe pas");
		}
	}

	@Override
	public void updateTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException {
		repo.save(tableRestaurant);
	}

	@Override
	public void deleteTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantServiceException {
		repo.delete(tableRestaurant);
	}

	@Override
	public List<TableRestaurant> getAvailableTablesFromRestaurant(LocalDateTime startTime, LocalDateTime endTime, Integer restaurantId) {
		return repo.findAvailableTablesFromRestaurant(startTime, endTime, restaurantId);
	}
	
	public List<TableRestaurantDTO> getAllTablesOccupees(Integer idRestau) {
	    List<TableRestaurant> toutesLesTables = getAllTableRestaurants();
	    List<Commande> commandesPassees = commandeService.getAllCommandesByStatut("passee");
	    
	    List<TableRestaurantDTO> tablesOccupees = toutesLesTables.stream()
	        .filter(table -> table.getRestaurant() != null && idRestau.equals(table.getRestaurant().getIdRestaurant()))
	        .filter(table -> table.getReservations() != null && !table.getReservations().isEmpty() && table
	            .getReservations().stream().anyMatch(reservation -> "arrivee".equals(reservation.getStatut())))
	        .map(table -> {
	            List<ReservationDTO> reservationsFiltrees = table.getReservations().stream()
	                .filter(reservation -> "arrivee".equals(reservation.getStatut()))
	                .map(reservation -> new ReservationDTO(reservation))
	                .collect(Collectors.toList());
	            
	            TableRestaurantDTO tableDTO = new TableRestaurantDTO(table, reservationsFiltrees);
	            
	            Optional<Commande> matchingCommande = commandesPassees.stream()
	                .filter(commande -> commande.getReservation() != null && 
	                    reservationsFiltrees.stream().anyMatch(res -> 
	                        res.getIdReservation().equals(commande.getReservation().getIdReservation())))
	                .findFirst();
	            
	            matchingCommande.ifPresent(commande -> tableDTO.setIdCommande(commande.getIdCommande()));
	            
	            return tableDTO;
	        })
	        .collect(Collectors.toList());
	    
	    return tablesOccupees;
	}
	
	

}
