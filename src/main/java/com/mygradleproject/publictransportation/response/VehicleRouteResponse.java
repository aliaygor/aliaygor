package com.mygradleproject.publictransportation.response;

import java.util.List;

import com.mygradleproject.publictransportation.entity.VehicleRoute;

public class VehicleRouteResponse extends BaseResponse{
	
	private VehicleRoute vehicleRoute;
	
	private List<VehicleRoute> vehicleRouteList;

	public VehicleRoute getVehicleRoute() {
		return vehicleRoute;
	}

	public void setVehicleRoute(VehicleRoute vehicleRoute) {
		this.vehicleRoute = vehicleRoute;
	}

	public List<VehicleRoute> getVehicleRouteList() {
		return vehicleRouteList;
	}

	public void setVehicleRouteList(List<VehicleRoute> vehicleRouteList) {
		this.vehicleRouteList = vehicleRouteList;
	}

}
