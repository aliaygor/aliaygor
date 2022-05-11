package com.mygradleproject.publictransportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.entity.Vehicle;
import com.mygradleproject.publictransportation.response.VehicleResponse;
import com.mygradleproject.publictransportation.service.VehicleService;
import com.mygradleproject.publictransportation.utils.RequestMeta;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
    private RequestMeta requestMeta;

	@GetMapping("/allVehicles")
	public VehicleResponse getAllVehicles(){

		return vehicleService.getAllVehicles();
		
	}
	
	@GetMapping("/getById/{id}")
	public VehicleResponse getVehicleById(@PathVariable("id") int vehicleId){

		return vehicleService.getVehicleById(vehicleId);
	}
	
	@PostMapping("/addOrUpdate")
	public VehicleResponse addOrUpdateVehicle(@RequestBody Vehicle vehicle){
		
		if(requestMeta != null) {
			User user = new User();
			user.setUserId(requestMeta.getUserId());
			user.setName(requestMeta.getName());
			user.setSurname(requestMeta.getSurname());
			user.setEmail(requestMeta.getEmail());
			vehicle.setUser(user);
		}
		
		return vehicleService.addOrUpdateVehicle(vehicle);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public VehicleResponse deleteVehicle(@PathVariable("id") int vehicleId){
		
		return vehicleService.deleteVehicle(vehicleId);

	}

}
