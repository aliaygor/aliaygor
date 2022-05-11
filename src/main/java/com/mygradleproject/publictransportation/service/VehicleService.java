package com.mygradleproject.publictransportation.service;

import com.mygradleproject.publictransportation.entity.Vehicle;
import com.mygradleproject.publictransportation.response.VehicleResponse;

public interface VehicleService {
	
	public VehicleResponse getAllVehicles();
	
	public VehicleResponse getVehicleById(int vehicleId);
	
	public VehicleResponse addOrUpdateVehicle(Vehicle vehicle);
	
	public VehicleResponse deleteVehicle(int vehicleId);

}
