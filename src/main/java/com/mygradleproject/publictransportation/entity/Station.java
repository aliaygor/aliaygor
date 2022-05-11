package com.mygradleproject.publictransportation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="station_name")
	private String stationName;
	@Column(name="total_buses")
	private int totalBuses;
	
	public Station() {
		super();
	}

	public Station(int id, String stationName, int totalBuses, User user, Date createDate) {
		super();
		this.id = id;
		this.stationName = stationName;
		this.totalBuses = totalBuses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getTotalBuses() {
		return totalBuses;
	}

	public void setTotalBuses(int totalBuses) {
		this.totalBuses = totalBuses;
	}

}
