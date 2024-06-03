package tos.gateocr.service;

import java.util.List;

import tos.gateocr.model.Plate;

public interface PlateService {
	List<Plate> getPlatesByPlate(String plate);

	Plate getLastPlateRead(String plate);

	List<Plate> getLatestPlates(int limit);
}
