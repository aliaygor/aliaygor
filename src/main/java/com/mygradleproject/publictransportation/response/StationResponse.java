package com.mygradleproject.publictransportation.response;

import java.util.List;

import com.mygradleproject.publictransportation.entity.Station;

public class StationResponse extends BaseResponse{
	
	private Station station;
	
	private List<Station> stationList;

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}
	
}
