package tos.gateocr.service;

import java.time.LocalDateTime;
import java.util.List;

import tos.gateocr.model.Plate;

public interface PlateService {
	List<Plate> getPlatesByPlate(String plate);

	Plate getLastPlateRead(String plate);

	Plate getLatestPlates();
	
	Plate getLastPlateByDate(LocalDateTime date);

}
