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
        return readsEntities.stream().map(plateMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Plate getLastPlateRead(String plate) {
        ReadsEntity readsEntity = readsRepository.findFirstByPlateOrderByTimestampLocalDesc(plate);
        return plateMapper.entityToModel(readsEntity);
    }

    @Override
    public Plate getLatestPlates() {
    	/*Calendar cal;
    	cal  = Calendar.getInstance();
    	cal.add(Calendar.MILLISECOND, -lastreadInterval);*/
    	
    	LocalDateTime ldt = LocalDateTime.now();
    	System.out.println(ldt.toLocalTime());
    	ldt = ldt.minusSeconds(lastreadInterval);
    	System.out.println(ldt.toLocalTime());
    	
        List<ReadsEntity> readsEntities = readsRepository.findLatestReads(ldt);
        if (readsEntities != null && readsEntities.size() > 0) {
        	return plateMapper.entityToModel(readsEntities.get(0));
        }
        return null;
    }
    
    @Override
    public Plate getLastPlateByDate(LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);
        List<ReadsEntity> readsEntities = readsRepository.findByDateRange(startOfDay, endOfDay);
        if (readsEntities != null && !readsEntities.isEmpty()) {
            return plateMapper.entityToModel(readsEntities.get(0));
        }
        return null;
    }

}
