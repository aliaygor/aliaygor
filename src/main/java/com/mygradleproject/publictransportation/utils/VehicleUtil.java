package com.mygradleproject.publictransportation.utils;

import org.springframework.stereotype.Component;

import com.mygradleproject.publictransportation.entity.Vehicle;

@Component
public class VehicleUtil {
	
	public static boolean isNullOrEmpty(String data) {
		if (data == null) {
			return true;
		}
		if (data.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public String controlVehicleProperty(Vehicle vehicle)
	{
		
		String result = null;
		
		if(isNullOrEmpty(vehicle.getBrand()))
			result = "brand cannot be empty";
		else if(isNullOrEmpty(vehicle.getPlate()))
			result = "plate cannot be empty";
		else if(vehicle.getTotalNumberPassenger() == 0)
			result = "total number passenger cannot be empty";
		else if(vehicle.getModelYear() == 0)
			result ="model year cannot be empty";
		else if(vehicle.getUser() == null)
			result ="user cannot be empty";
		
		return result;
	}
}
