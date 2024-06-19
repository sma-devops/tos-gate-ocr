package tos.gateocr.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tos.gateocr.entity.ReadsEntity;
import tos.gateocr.mapper.PlateMapper;
import tos.gateocr.model.Plate;
import tos.gateocr.repository.ReadsRepository;
import tos.gateocr.service.PlateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlateServiceImpl implements PlateService {

    @Value("${app.lastread.interval}")
    private int lastreadInterval;

    private final ReadsRepository readsRepository;
    private final PlateMapper plateMapper;

    public PlateServiceImpl(ReadsRepository readsRepository, PlateMapper plateMapper) {
        this.readsRepository = readsRepository;
        this.plateMapper = plateMapper;
    }

    @Override
    public List<Plate> getPlatesByPlate(String plate) {
        List<ReadsEntity> readsEntities = readsRepository.findByPlate(plate);
        return readsEntities.stream()
                            .map(plateMapper::entityToModel)
                            .collect(Collectors.toList());
    }

    @Override
    public Plate getLastPlateRead(String plate) {
        ReadsEntity readsEntity = readsRepository.findFirstByPlateOrderByTimestampLocalDesc(plate);
        return plateMapper.entityToModel(readsEntity);
    }

    @Override
    public Plate getLatestPlate() {
        LocalDateTime dateTime = LocalDateTime.now().minusNanos(lastreadInterval * 1000000); // Adjust for millisecond interval
        List<ReadsEntity> readsEntities = readsRepository.findLatestReads(dateTime);
        if (!readsEntities.isEmpty()) {
            return plateMapper.entityToModel(readsEntities.get(0));
        }
        return null;
    }

    @Override
    public List<Plate> getPlatesReadAfter(LocalDateTime dateTime) {
        List<ReadsEntity> readsEntities = readsRepository.findAllByTimestampLocalAfter(dateTime);
        return readsEntities.stream()
                            .map(plateMapper::entityToModel)
                            .collect(Collectors.toList());
    }
    @Override
    public Plate getLastPlateReadAfterSameHourMinute(LocalDateTime dateTime) {
        LocalDateTime truncatedDateTime = LocalDateTime.of(
                dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), 0, 0);

        List<ReadsEntity> readsEntities = readsRepository.findAllByTimestampLocalAfter(truncatedDateTime);

        Optional<ReadsEntity> matchingEntity = readsEntities.stream()
                .filter(entity -> {
                    LocalDateTime entityDateTime = entity.getTimestampLocal();
                    return entityDateTime.getHour() == dateTime.getHour() &&
                           entityDateTime.getMinute() == dateTime.getMinute();
                })
                .findFirst();

        return matchingEntity.map(plateMapper::entityToModel).orElse(null);
    }

}
