package fr.formation.fil_rouge_YOLO_part3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.fil_rouge_YOLO_part3.entity.TableRestaurant;
import fr.formation.fil_rouge_YOLO_part3.repository.TableRestaurantRepository;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {
	@Autowired
	TableRestaurantRepository repo;

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
//		List<TableRestaurant> tablesLibresToutesResas = repo.findAvailableTablesFromRestaurant(startTime, endTime, restaurantId);
//		
//		List<TableRestaurant> tablesLibresFutures = tablesLibresToutesResas.stream()
//			    .filter(table -> table.getReservations().isEmpty() || // Garde les tables sans réservation
//			        table.getReservations().stream()
//			            .noneMatch(reservation -> reservation.getHoraireReservation().isBefore(LocalDateTime.now())))
//			    .collect(Collectors.toList());
//		
//		return tablesLibresFutures;

		return repo.findAvailableTablesFromRestaurant(startTime, endTime, restaurantId);
	}
	
	

}
