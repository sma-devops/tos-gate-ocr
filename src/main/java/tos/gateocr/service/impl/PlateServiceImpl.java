package tos.gateocr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tos.gateocr.entity.ReadsEntity;
import tos.gateocr.mapper.PlateMapper;
import tos.gateocr.model.Plate;
import tos.gateocr.repository.ReadsRepository2;
import tos.gateocr.service.PlateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlateServiceImpl implements PlateService {

    private final ReadsRepository2 readsRepository;
    private final PlateMapper plateMapper;

    
    public PlateServiceImpl(ReadsRepository2 readsRepository, PlateMapper plateMapper) {
        this.readsRepository = readsRepository;
        this.plateMapper = plateMapper;
    }

    @Override
    public List<Plate> getPlatesByPlate(String plate) {
        List<ReadsEntity> readsEntities = readsRepository.findByPlate(plate);
        return readsEntities.stream().map(plateMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Plate getLastPlateRead(String plate) {
        ReadsEntity readsEntity = readsRepository.findFirstByPlateOrderByTimestampLocalDesc(plate);
        return plateMapper.entityToModel(readsEntity);
    }

    @Override
    public List<Plate> getLatestPlates(int limit) {
        Page<ReadsEntity> pageResult = readsRepository.findLatestReads(PageRequest.of(0, limit));
        List<ReadsEntity> readsEntities = pageResult.getContent();
        return readsEntities.stream().map(plateMapper::entityToModel).collect(Collectors.toList());
    }
}
