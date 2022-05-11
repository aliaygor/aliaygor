package com.mygradleproject.publictransportation.service;

import com.mygradleproject.publictransportation.entity.VehicleRoute;
import com.mygradleproject.publictransportation.response.VehicleRouteResponse;

public interface VehicleRouteService {
	
	public VehicleRouteResponse getAllVehicleRoutes();
	
	public VehicleRouteResponse getVehicleRouteById(int vehicleRouteId);
	
	public VehicleRouteResponse addOrUpdateVehicleRoute(VehicleRoute vehicleRoute);
	
	public VehicleRouteResponse deleteVehicleRoute(int vehicleRouteId);

}
