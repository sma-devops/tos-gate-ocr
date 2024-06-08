package tos.gateocr.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tos.gateocr.entity.ReadsEntity;

public interface ReadsRepository extends JpaRepository<ReadsEntity, Long> {

    List<ReadsEntity> findByPlate(String plate);

    List<ReadsEntity> findByPlateAndPlateState(String plate, String state);

    @Query("SELECT r FROM ReadsEntity r WHERE LOWER(r.plate) LIKE LOWER(?1) AND r.plateState = ?2")
    List<ReadsEntity> findByPlateFlagAndPlateState(String plate, String state);

    @Query("SELECT r FROM ReadsEntity r where r.timestampLocal >= ?1 ORDER BY r.timestampLocal DESC")
    List<ReadsEntity> findLatestReads(LocalDateTime limit);

    ReadsEntity findFirstByPlateOrderByTimestampLocalDesc(String plate);
    
    @Query("SELECT r FROM ReadsEntity r WHERE r.timestampLocal BETWEEN :startDate AND :endDate ORDER BY r.timestampLocal DESC")
    List<ReadsEntity> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

}
