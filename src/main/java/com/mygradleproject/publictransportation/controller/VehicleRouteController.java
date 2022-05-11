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
import com.mygradleproject.publictransportation.entity.VehicleRoute;
import com.mygradleproject.publictransportation.response.VehicleRouteResponse;
import com.mygradleproject.publictransportation.service.VehicleRouteService;
import com.mygradleproject.publictransportation.utils.RequestMeta;

@RestController
@RequestMapping("/vehicleRoute")
public class VehicleRouteController {
	
	@Autowired
	private VehicleRouteService vehicleRouteService;
	
	@Autowired
    private RequestMeta requestMeta;

	@GetMapping("/allVehicleRoutes")
	public VehicleRouteResponse getAllVehicleRoutes(){

		return vehicleRouteService.getAllVehicleRoutes();
		
	}
	
	@GetMapping("/getById/{id}")
	public VehicleRouteResponse getVehicleRouteById(@PathVariable("id") int vehicleRouteId){

		return vehicleRouteService.getVehicleRouteById(vehicleRouteId);
	}
	
	@PostMapping("/addOrUpdate")
	public VehicleRouteResponse addOrUpdateVehicleRoute(@RequestBody VehicleRoute vehicleRoute){
		
		if(requestMeta != null) {
			User user = new User();
			user.setUserId(requestMeta.getUserId());
			user.setName(requestMeta.getName());
			user.setSurname(requestMeta.getSurname());
			user.setEmail(requestMeta.getEmail());
			vehicleRoute.setUser(user);
		}
		
		return vehicleRouteService.addOrUpdateVehicleRoute(vehicleRoute);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public VehicleRouteResponse deleteVehicleRoute(@PathVariable("id") int vehicleRouteId){

		return vehicleRouteService.deleteVehicleRoute(vehicleRouteId);

	}

}
