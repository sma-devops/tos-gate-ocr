package tos.gateocr.mapper.impl;

import org.springframework.stereotype.Component;
import tos.gateocr.entity.ReadsEntity;
import tos.gateocr.mapper.PlateMapper;
import tos.gateocr.model.Plate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlateMapperImpl implements PlateMapper {

    @Override
    public Plate entityToModel(ReadsEntity entity) {
        if (entity == null) {
            return null;
        }

        Plate plate = new Plate();
        plate.setId(entity.getId() != null ? entity.getId().intValue() : null);
        plate.setPlate(entity.getPlate());
        plate.setPlateState(entity.getPlateState());
        plate.setLongitude(entity.getLongitude());
        plate.setLatitude(entity.getLatitude() != null ? entity.getLatitude().floatValue() : null);
        plate.setTimestampLocal(entity.getTimestampLocal());

        return plate;
    }

    @Override
    public ReadsEntity modelToEntity(Plate model) {
        if (model == null) {
            return null;
        }

        ReadsEntity entity = new ReadsEntity();
        entity.setId(model.getId() != null ? model.getId().longValue() : null);
        entity.setPlate(model.getPlate());
        entity.setPlateState(model.getPlateState());
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude() != null ? model.getLatitude().doubleValue() : null);
        entity.setTimestampLocal(model.getTimestampLocal());

        return entity;
    }

    @Override
    public List<Plate> entityListToModelList(List<ReadsEntity> entities) {
        return entities.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    @Override
    public List<Plate> entityListToModelArrayList(List<ReadsEntity> entities) {
        return entities.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Plate map(ReadsEntity source) {
        return entityToModel(source);
    }
}
