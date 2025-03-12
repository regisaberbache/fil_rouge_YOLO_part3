package fr.formation.fil_rouge_YOLO_part3.service;

import java.util.List;

import fr.formation.fil_rouge_YOLO_part3.entity.Plat;

public interface PlatService {
	void createPlat(Plat plat);
	List<Plat> getAllPlats();
	Plat getPlatById(Integer id) throws PlatServiceException;
	void updatePlat(Plat plat);
	void deletePlat(Plat plat);

	
}
