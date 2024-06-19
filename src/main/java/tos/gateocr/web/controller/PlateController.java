package tos.gateocr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tos.gateocr.model.Plate;
import tos.gateocr.service.PlateService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/plates")
public class PlateController {

    @Autowired
    private PlateService plateService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");

    @GetMapping("/{plate}")
    public ResponseEntity<List<Plate>> getPlatesByPlate(@PathVariable String plate) {
        List<Plate> foundPlates = plateService.getPlatesByPlate(plate);
        return foundPlates.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(foundPlates);
    }

    @GetMapping("/{plate}/last")
    public ResponseEntity<Plate> getLastPlateRead(@PathVariable String plate) {
        Plate lastPlate = plateService.getLastPlateRead(plate);
        return lastPlate != null ? ResponseEntity.ok(lastPlate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<Plate> getLatestPlate() {
        Plate latestPlate = plateService.getLatestPlate();
        return latestPlate != null ? ResponseEntity.ok(latestPlate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/last-read-after/{timestamp}")
    public ResponseEntity<List<Plate>> getPlatesReadAfter(@PathVariable("timestamp") String timestamp) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timestamp.replace("%20", " "), DATE_TIME_FORMATTER);
            List<Plate> plates = plateService.getPlatesReadAfter(dateTime);
            return plates.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(plates);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
