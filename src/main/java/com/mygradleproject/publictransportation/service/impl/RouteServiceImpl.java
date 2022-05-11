package com.mygradleproject.publictransportation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.Route;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.repository.RouteRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.response.RouteResponse;
import com.mygradleproject.publictransportation.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepository routeRepository;
	
    @Autowired
    private LogRepository logRepository;

	@Override
	public RouteResponse getAllRoutes() {
		
		RouteResponse response = new RouteResponse();
		
		List<Route> routeList = new ArrayList<>();
		
		try {
			routeList = routeRepository.findAll();
			if(routeList == null) {
				response.setAdditionalInfo("Route is not found");
			}
			routeList.parallelStream().forEach(p -> p.getUser().setPassword(null));
			response.setRouteList(routeList);
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
	public RouteResponse getRouteById(int routeId) {
		
		RouteResponse response = new RouteResponse();
		
		Route route = new Route();
		try {
			route = routeRepository.findById(routeId).orElse(null);
			if(route == null) {
				response.setAdditionalInfo("kayit bulunamadi");
			}
			route.getUser().setPassword(null);
			response.setRoute(route);
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
	public RouteResponse addOrUpdateRoute(Route route) {
		
		RouteResponse response = new RouteResponse();
		boolean isExist = false;
		try {
			
			isExist = routeRepository.existsById(route.getId());
			
			if(route.getStartStation() == null || route.getFinishStation() == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				response.setAdditionalInfo("station information cannot be empty");
				return response;
			}
			
			if(route.getCreateDate() == null)
				route.setCreateDate(new Date());
			
			route = routeRepository.save(route);
			logRepository.insertLog("route is " + (isExist == true ? "updated" : "inserted") + " start station: " + route.getStartStation().getStationName() + " finish station: " + route.getFinishStation().getStationName() , 
					route.getUser().getUserId());
			response.setRoute(route);
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
	public RouteResponse deleteRoute(int routeId) {
		
		RouteResponse response = new RouteResponse(); 
		
		Route deletedRoute = null;
		try {
			deletedRoute = routeRepository.findById(routeId).orElse(null);
			if(deletedRoute == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setOperationMessage(BaseResponse.UNSUCCESS);
				response.setAdditionalInfo("Route not found");
			}else {
				routeRepository.deleteById(routeId);
				logRepository.insertLog("deleted route is " + deletedRoute.getStartStation().getStationName() + " - " + deletedRoute.getFinishStation().getStationName() , 
						deletedRoute.getUser().getUserId());
				response.setStatus(HttpStatus.OK.value());
				response.setOperationMessage(BaseResponse.SUCCESS);
				response.setAdditionalInfo("Route is deleted");
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
	}


}
