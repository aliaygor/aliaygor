package com.mygradleproject.publictransportation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.VehicleRoute;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.repository.VehicleRouteRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.response.VehicleRouteResponse;
import com.mygradleproject.publictransportation.service.VehicleRouteService;
import com.mygradleproject.publictransportation.utils.VehicleUtil;

@Service
public class VehicleRouteServiceImpl implements VehicleRouteService{
	
	@Autowired
	private VehicleRouteRepository vehicleRouteRepository;
	
    @Autowired
    private LogRepository logRepository;
    
    @Autowired
    private VehicleUtil vehicleUtil;

	@Override
	public VehicleRouteResponse getAllVehicleRoutes() {
		
		VehicleRouteResponse response = new VehicleRouteResponse();
		
		List<VehicleRoute> vehicleRouteList = new ArrayList<>();
		
		try {
			vehicleRouteList = vehicleRouteRepository.findAll();
			if(vehicleRouteList == null) {
				response.setAdditionalInfo("VehicleRoute is not found");
			}
			vehicleRouteList.parallelStream().forEach(p -> p.getUser().setPassword(null));
			response.setVehicleRouteList(vehicleRouteList);
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
	public VehicleRouteResponse getVehicleRouteById(int vehicleRouteId) {
		
		VehicleRouteResponse response = new VehicleRouteResponse();
		
		VehicleRoute vehicleRoute = new VehicleRoute();
		try {
			vehicleRoute = vehicleRouteRepository.findById(vehicleRouteId).orElse(null);
			if(vehicleRoute == null) {
				response.setAdditionalInfo("kayit bulunamadi");
			}
			vehicleRoute.getUser().setPassword(null);
			response.setVehicleRoute(vehicleRoute);
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
	public VehicleRouteResponse addOrUpdateVehicleRoute(VehicleRoute vehicleRoute) {
		
		VehicleRouteResponse response = new VehicleRouteResponse();
		
		boolean isExist = false;
		try {
			
			isExist = vehicleRouteRepository.existsById(vehicleRoute.getId());
			
			if(vehicleRoute.getCreateDate() == null)
				vehicleRoute.setCreateDate(new Date());
			
			String additionalInfo = vehicleUtil.controlVehicleProperty(vehicleRoute.getVehicle());
			
			if(additionalInfo != null) {
				response.setAdditionalInfo(additionalInfo);
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				return response;
			}
			
			vehicleRoute = vehicleRouteRepository.save(vehicleRoute);
			
			logRepository.insertLog((isExist == true ? "updated" : "inserted") + " route to vehicle. vehicle id: " + vehicleRoute.getVehicle().getId() + " route number: " + vehicleRoute.getRoute().getRouteNum()
					,vehicleRoute.getUser().getUserId());
			response.setVehicleRoute(vehicleRoute);
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
	public VehicleRouteResponse deleteVehicleRoute(int vehicleRouteId) {
		
		VehicleRouteResponse response = new VehicleRouteResponse(); 
		
		VehicleRoute deletedVehicleRoute = null;
		try {
			deletedVehicleRoute = vehicleRouteRepository.findById(vehicleRouteId).orElse(null);
			if(deletedVehicleRoute == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				response.setAdditionalInfo("VehicleRoute not found");
			}else {
				vehicleRouteRepository.deleteById(vehicleRouteId);
				logRepository.insertLog("route deleted from vehicle. vehicle id: " + deletedVehicleRoute.getVehicle().getId() + " route number: " + deletedVehicleRoute.getRoute().getRouteNum() , 
						deletedVehicleRoute.getUser().getUserId());
				response.setStatus(HttpStatus.OK.value());
				response.setOperationMessage(BaseResponse.SUCCESS);
				response.setAdditionalInfo("VehicleRoute is deleted");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
	}


}
