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
    public Plate entityToModel(ReadsEntity readsEntity) {
        Plate plate = new Plate();
        plate.setId(readsEntity.getId());
        plate.setPlate(readsEntity.getPlate());
        plate.setPlateState(readsEntity.getPlateState());
        plate.setLongitude(readsEntity.getLongitude());
        plate.setLatitude(readsEntity.getLatitude().floatValue());
        plate.setTimestampLocal(readsEntity.getTimestampLocal().toString());
        return plate;
    }

    @Override
    public List<Plate> entityListToModelArrayList(List<ReadsEntity> readsEntities) {
        return readsEntities.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Plate map(ReadsEntity source) {
        return entityToModel(source);
    }
}
