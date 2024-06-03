package tos.gateocr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tos.gateocr.entity.ReadsEntity;

public interface ReadsRepository2 extends JpaRepository<ReadsEntity, Long> {

    List<ReadsEntity> findByPlate(String plate);

    List<ReadsEntity> findByPlateAndPlateState(String plate, String state);

    @Query("SELECT r FROM ReadsEntity r WHERE LOWER(r.plate) LIKE LOWER(?1) AND r.plateState = ?2")
    List<ReadsEntity> findByPlateFlagAndPlateState(String plate, String state);

    @Query("SELECT r FROM ReadsEntity r ORDER BY r.timestampLocal DESC")
    Page<ReadsEntity> findLatestReads(org.springframework.data.domain.Pageable pageable);

    ReadsEntity findFirstByPlateOrderByTimestampLocalDesc(String plate);
}
