package tos.gateocr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tos.gateocr.model.Plate;
import tos.gateocr.service.PlateService;

import java.util.List;

@RestController
public class PlateController {

    @Autowired
    private PlateService plateService;

    @GetMapping("/api/plates/{plate}")
    public ResponseEntity<List<Plate>> getPlatesByPlate(@PathVariable String plate) {
        List<Plate> foundPlates = plateService.getPlatesByPlate(plate);
        if (foundPlates != null && !foundPlates.isEmpty()) {
            return ResponseEntity.ok(foundPlates);
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
    public ResponseEntity<Plate> getLatestPlate() {  
        Plate latestPlate = plateService.getLatestPlate();
        if (latestPlate != null) {
            return ResponseEntity.ok(latestPlate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
