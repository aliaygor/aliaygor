package com.mygradleproject.publictransportation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.Station;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.repository.StationRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.response.StationResponse;
import com.mygradleproject.publictransportation.service.StationService;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private LogRepository logRepository;

	@Override
	public StationResponse getAllStations() {

		StationResponse response = new StationResponse();

		List<Station> stationList = new ArrayList<>();

		try {
			stationList = stationRepository.findAll();

			response.setStationList(stationList);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
			if (stationList == null) {
				response.setAdditionalInfo("Station is not found");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;

	}

	@Override
	public StationResponse getStationById(int stationId) {

		StationResponse response = new StationResponse();

		Station station = new Station();
		try {
			station = stationRepository.findById(stationId).orElse(null);
			response.setStation(station);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
			if (station == null) {
				response.setAdditionalInfo("kayit bulunamadi");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}

		return response;
	}

	@Override
	public StationResponse addOrUpdateStation(Station station, int userId) {

		StationResponse response = new StationResponse();
		boolean isExist = false;
		try {
			
			isExist = stationRepository.existsById(station.getId());
			
			station = stationRepository.save(station);
			logRepository.insertLog("station is " + (isExist == true ? "updated" : "inserted") + " station name is " + station.getStationName() , userId);
			response.setStation(station);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}

		return response;

	}

	@Override
	public StationResponse deleteStation(int stationId, int userId) {

		StationResponse response = new StationResponse();

		Station deletedStation = null;
		try {
			deletedStation = stationRepository.findById(stationId).orElse(null);
			if (deletedStation == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				response.setAdditionalInfo("Station not found");
			} else {
				stationRepository.deleteById(stationId);
				logRepository.insertLog("deleted station : " + deletedStation.getStationName() , userId);
				response.setStatus(HttpStatus.OK.value());
				response.setOperationMessage(BaseResponse.SUCCESS);
				response.setAdditionalInfo("Station is deleted");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
	}

}
