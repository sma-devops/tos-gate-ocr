package tos.gateocr.mapper;

import tos.gateocr.entity.ReadsEntity;
import tos.gateocr.model.Plate;
import java.util.List;

public interface PlateMapper extends AbstractMapper<ReadsEntity, Plate> {
    Plate entityToModel(ReadsEntity readsEntity);

    List<Plate> entityListToModelArrayList(List<ReadsEntity> readsEntities);
}
