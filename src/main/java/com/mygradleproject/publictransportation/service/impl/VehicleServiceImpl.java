package com.mygradleproject.publictransportation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.Vehicle;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.repository.VehicleRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.response.VehicleResponse;
import com.mygradleproject.publictransportation.service.VehicleService;
import com.mygradleproject.publictransportation.utils.VehicleUtil;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
    @Autowired
    private LogRepository logRepository;
    
    @Autowired
    private VehicleUtil vehicleUtil;

	@Override
	public VehicleResponse getAllVehicles() {
		
		VehicleResponse response = new VehicleResponse();
		
		List<Vehicle> vehicleList = new ArrayList<>();
		
		try {
			vehicleList = vehicleRepository.findAll();
			
			response.setVehicleList(vehicleList);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
			if(vehicleList == null) {
				response.setAdditionalInfo("vehicle is not found");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
		
	}

	@Override
	public VehicleResponse getVehicleById(int vehicleId) {
		
		VehicleResponse response = new VehicleResponse();
		
		Vehicle vehicle = new Vehicle();
		try {
			vehicle = vehicleRepository.findById(vehicleId).orElse(null);
			response.setVehicle(vehicle);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
			if(vehicle == null) {
				response.setAdditionalInfo("vehicle is not found");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		
		return response;
	}
	
	@Override
	public VehicleResponse addOrUpdateVehicle(Vehicle vehicle) {
		
		VehicleResponse response = new VehicleResponse();
		boolean isExist = false;
		try {
			
			String additionalInfo = vehicleUtil.controlVehicleProperty(vehicle);
			
			if(additionalInfo != null) {
				response.setAdditionalInfo(additionalInfo);
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				return response;
			}
			
			isExist = vehicleRepository.existsById(vehicle.getId());
			
			if(vehicle.getCreateDate() == null)
				vehicle.setCreateDate(new Date());
				
			vehicle = vehicleRepository.save(vehicle);
			
			if(vehicle != null)
				logRepository.insertLog("vehicle is " + (isExist == true ? "updated" : "inserted") + 
						" vehicle brand: " + vehicle.getBrand() + " plate: " + vehicle.getPlate() +
						" total passenger: " + vehicle.getTotalNumberPassenger() + " model year: " + vehicle.getModelYear() ,
						vehicle.getUser().getUserId());
			
			response.setAdditionalInfo("vehicle is added sucessfully");
			response.setVehicle(vehicle);
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
	public VehicleResponse deleteVehicle(int vehicleId) {
		
		VehicleResponse response = new VehicleResponse(); 
		
		Vehicle deletedVehicle = null;
		try {
			deletedVehicle = vehicleRepository.findById(vehicleId).orElse(null);
			if(deletedVehicle == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				response.setAdditionalInfo("vehicle not found");
			}else {
				vehicleRepository.deleteById(vehicleId);
				logRepository.insertLog("deleted vehicle brand: " + deletedVehicle.getBrand() + " plate: " + deletedVehicle.getPlate() + " total passenger: " + deletedVehicle.getTotalNumberPassenger() + " model year: " + deletedVehicle.getModelYear() , 
						deletedVehicle.getUser().getUserId());
				response.setStatus(HttpStatus.OK.value());
				response.setOperationMessage(BaseResponse.SUCCESS);
				response.setAdditionalInfo("vehicle is deleted");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
	}


}
