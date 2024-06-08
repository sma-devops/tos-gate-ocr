package tos.gateocr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tos.gateocr.model.Plate;
import tos.gateocr.service.PlateService;

@RestController
public class PlateController {

	@Autowired
	private PlateService plateService;

	@GetMapping("/api/plates/{plate}")
	public ResponseEntity<Plate> getPlatesByPlate(@PathVariable String plate) {
		Plate foundPlate = (Plate) plateService.getPlatesByPlate(plate);
		if (foundPlate != null) {
			return ResponseEntity.ok(foundPlate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/api/plates/{plate}/last")
	public ResponseEntity<Plate> getLastPlateRead(@PathVariable String plate) {
		Plate lastPlate = plateService.getLastPlateRead(plate);
		if (lastPlate != null) {
			return ResponseEntity.ok(lastPlate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/api/plates/latest")
	public ResponseEntity<Plate> getLatestPlates() {
		Plate latestPlate = plateService.getLatestPlates();
		return ResponseEntity.ok(latestPlate);
	}
}