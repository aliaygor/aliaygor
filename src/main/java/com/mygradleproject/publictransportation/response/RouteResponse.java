package com.mygradleproject.publictransportation.response;

import java.util.List;

import com.mygradleproject.publictransportation.entity.Route;

public class RouteResponse extends BaseResponse{
	
	private Route route;
	
	private List<Route> routeList;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	
}
