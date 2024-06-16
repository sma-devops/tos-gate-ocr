package tos.gateocr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reads", schema = "dbo")
public class ReadsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Plate")
    private String plate;

    @Column(name = "Platestate")
    private String plateState;

    @Column(name = "Longitude")
    private Double longitude;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Timestamplocal")
    private LocalDateTime timestampLocal;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlateState() {
        return plateState;
    }

    public void setPlateState(String plateState) {
        this.plateState = plateState;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public LocalDateTime getTimestampLocal() {
        return timestampLocal;
    }

    public void setTimestampLocal(LocalDateTime timestampLocal) {
        this.timestampLocal = timestampLocal;
    }

    // Méthode pour comparer les dates sans fractions de secondes
    public LocalDate getDateWithoutTime() {
        return timestampLocal.toLocalDate();
    }

    @Override
    public String toString() {
        return "ReadsEntity{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", plateState='" + plateState + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", timestampLocal=" + timestampLocal +
                '}';
    }
}
