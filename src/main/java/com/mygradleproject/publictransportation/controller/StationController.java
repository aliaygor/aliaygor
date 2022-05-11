package com.mygradleproject.publictransportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygradleproject.publictransportation.entity.Station;
import com.mygradleproject.publictransportation.response.StationResponse;
import com.mygradleproject.publictransportation.service.StationService;
import com.mygradleproject.publictransportation.utils.RequestMeta;

@RestController
@RequestMapping("/station")
public class StationController {
	
	@Autowired
	private StationService stationService;
	
	@Autowired
    private RequestMeta requestMeta;

	@GetMapping("/allStations")
	public StationResponse getAllStations(){
		return stationService.getAllStations();
	}
	
	@GetMapping("/getById/{id}")
	public StationResponse getStationById(@PathVariable("id") int stationId){

		return stationService.getStationById(stationId);
	}
	
	@PostMapping("/addOrUpdate")
	public StationResponse addOrUpdateStation(@RequestBody Station station){
		
		return stationService.addOrUpdateStation(station, requestMeta.getUserId());
		
	}
	
	@DeleteMapping("/delete/{id}")
	public StationResponse deleteStation(@PathVariable("id") int stationId){

		return stationService.deleteStation(stationId, requestMeta.getUserId());

	}

}
