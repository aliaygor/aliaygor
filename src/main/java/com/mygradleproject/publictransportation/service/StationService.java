package com.mygradleproject.publictransportation.service;

import com.mygradleproject.publictransportation.entity.Station;
import com.mygradleproject.publictransportation.response.StationResponse;

public interface StationService {
	
	public StationResponse getAllStations();
	
	public StationResponse getStationById(int stationId);
	
	public StationResponse addOrUpdateStation(Station station, int userId);
	
	public StationResponse deleteStation(int stationId, int userId);

}
