package tos.gateocr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tos.gateocr.model.Plate;
import tos.gateocr.service.PlateService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class PlateController {

    @Autowired
    private PlateService plateService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");

    @GetMapping("/api/plates/{plate}")
    public ResponseEntity<List<Plate>> getPlatesByPlate(@PathVariable String plate) {
        List<Plate> foundPlates = plateService.getPlatesByPlate(plate);
        return foundPlates.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(foundPlates);
    }

    @GetMapping("/api/plates/{plate}/last")
    public ResponseEntity<Plate> getLastPlateRead(@PathVariable String plate) {
        Plate lastPlate = plateService.getLastPlateRead(plate);
        return lastPlate != null ? ResponseEntity.ok(lastPlate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/plates/latest")
    public ResponseEntity<Plate> getLatestPlate() {
        Plate latestPlate = plateService.getLatestPlate();
        return latestPlate != null ? ResponseEntity.ok(latestPlate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/plates/last-read-after/{timestamp}")
    public ResponseEntity<List<Plate>> getPlatesReadAfter(@PathVariable("timestamp") String timestamp) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
            List<Plate> plates = plateService.getPlatesReadAfter(dateTime);
            return plates.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(plates);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/api/plates/last-read-same-hour-minute-after/{timestamp}")
    public ResponseEntity<Plate> getLastPlateReadAfterSameHourMinute(@PathVariable("timestamp") String timestamp) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
            Plate plate = plateService.getLastPlateReadAfterSameHourMinute(dateTime);
            return plate != null ? ResponseEntity.ok(plate) : ResponseEntity.notFound().build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
