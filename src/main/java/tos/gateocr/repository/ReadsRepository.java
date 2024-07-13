package tos.gateocr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tos.gateocr.entity.ReadsEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ReadsRepository extends JpaRepository<ReadsEntity, Long> {

    List<ReadsEntity> findByPlate(String plate);

    ReadsEntity findFirstByPlateOrderByTimestampLocalDesc(String plate);
    
    @Query("SELECT r FROM ReadsEntity r WHERE r.timestampLocal > :dateTime ORDER BY r.timestampLocal DESC")
    List<ReadsEntity> findLatestReads(@Param("dateTime") LocalDateTime dateTime);

    List<ReadsEntity> findAllByTimestampLocalAfterOrderByTimestampLocalDesc(LocalDateTime dateTime);
}
