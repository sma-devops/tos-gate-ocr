package tos.gateocr.model;

import java.time.LocalDateTime;

public class Plate {
	private Integer id;
	private String plate;
	private String plateState;
	private Double longitude;

	private Float latitude;
	private String timestampLocal;

	// Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setLongitude(double f) {
		this.longitude = f;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getTimestampLocal() {
		return timestampLocal;
	}

	public void setTimestampLocal(String timestampLocal) {
		this.timestampLocal = timestampLocal;
	}

	@Override
	public String toString() {
		return "Plate{" + "id=" + id + ", plate='" + plate + '\'' + ", plateState='" + plateState + '\''
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", timestampLocal='" + timestampLocal + '\'' + '}';
	}

	public void setTimestampLocal(LocalDateTime timestampLocal2) {
		// TODO Auto-generated method stub

	}
}
