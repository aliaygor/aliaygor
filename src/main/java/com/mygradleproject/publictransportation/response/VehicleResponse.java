package com.mygradleproject.publictransportation.response;

import java.util.List;

import com.mygradleproject.publictransportation.entity.Vehicle;

public class VehicleResponse extends BaseResponse{
	
	private Vehicle vehicle;
	
	private List<Vehicle> vehicleList;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	
}
