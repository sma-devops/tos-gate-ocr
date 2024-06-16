package tos.gateocr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tos.gateocr.entity.ReadsEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ReadsRepository extends JpaRepository<ReadsEntity, Long> {
    List<ReadsEntity> findByPlate(String plate);
    ReadsEntity findFirstByPlateOrderByTimestampLocalDesc(String plate);
    List<ReadsEntity> findAllByTimestampLocalAfter(LocalDateTime dateTime);
    
    @Query("SELECT r FROM ReadsEntity r WHERE r.timestampLocal > :timestampLocal " +
           "ORDER BY r.timestampLocal DESC")
    List<ReadsEntity> findLatestReads(LocalDateTime timestampLocal);
    
    @Query("SELECT r FROM ReadsEntity r WHERE r.timestampLocal > :timestampLocal AND " +
           "FUNCTION('HOUR', r.timestampLocal) = FUNCTION('HOUR', :timestampLocal) AND " +
           "FUNCTION('MINUTE', r.timestampLocal) = FUNCTION('MINUTE', :timestampLocal) " +
           "ORDER BY r.timestampLocal DESC")
    List<ReadsEntity> findAllByTimestampLocalAfterSameHourMinute(LocalDateTime timestampLocal);
}
