package com.mygradleproject.publictransportation.service;

import com.mygradleproject.publictransportation.entity.Route;
import com.mygradleproject.publictransportation.response.RouteResponse;

public interface RouteService {
	
	public RouteResponse getAllRoutes();
	
	public RouteResponse getRouteById(int routeId);
	
	public RouteResponse addOrUpdateRoute(Route route);
	
	public RouteResponse deleteRoute(int routeId);

}
