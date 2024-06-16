package tos.gateocr.mapper;

import tos.gateocr.entity.ReadsEntity;
import tos.gateocr.model.Plate;

import java.time.LocalDateTime;  // Assurez-vous d'importer LocalDateTime depuis java.time.*
import java.util.List;

public interface PlateMapper extends AbstractMapper<ReadsEntity, Plate> {
    Plate entityToModel(ReadsEntity entity);

    ReadsEntity modelToEntity(Plate model);

    List<Plate> entityListToModelList(List<ReadsEntity> entities);
    
    List<Plate> entityListToModelArrayList(List<ReadsEntity> entities); // Nouvelle méthode si nécessaire
}
