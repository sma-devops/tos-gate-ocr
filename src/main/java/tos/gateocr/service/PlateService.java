package tos.gateocr.service;

import tos.gateocr.model.Plate;

import java.time.LocalDateTime;
import java.util.List;

public interface PlateService {
    List<Plate> getPlatesByPlate(String plate);
    Plate getLastPlateRead(String plate);
    Plate getLatestPlate();
    List<Plate> getPlatesReadAfter(LocalDateTime dateTime);
    //Plate getLastPlateReadAfterSameHourMinute(LocalDateTime dateTime);
}
