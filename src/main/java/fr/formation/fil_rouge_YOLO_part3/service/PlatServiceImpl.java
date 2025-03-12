package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.fil_rouge_YOLO_part3.entity.Plat;
import fr.formation.fil_rouge_YOLO_part3.repository.PlatRepository;

public class PlatServiceImpl implements PlatService {
	@Autowired
	PlatRepository repo;
	
	@Override
	public void createPlat(Plat plat) {
		repo.save(plat);
		
	}

	@Override
	public List<Plat> getAllPlats() {
		return repo.findAll();
	}

	@Override
	public Plat getPlatById(Integer id) throws PlatServiceException {
		Optional<Plat> plat = repo.findById(id);
		if(plat.isPresent()) {
			return plat.get();
		}
		else {
			throw new PlatServiceException("Cet identifiant n'existe pas");
		}
	}

	@Override
	public void updatePlat(Plat plat) {
		repo.save(plat);
		
	}

	@Override
	public void deletePlat(Plat plat) {
		repo.delete(plat);
		
	}

}
