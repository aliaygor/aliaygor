package com.mygradleproject.publictransportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygradleproject.publictransportation.entity.Route;
import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.response.RouteResponse;
import com.mygradleproject.publictransportation.service.RouteService;
import com.mygradleproject.publictransportation.utils.RequestMeta;

@RestController
@RequestMapping("/route")
public class RouteController {
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
    private RequestMeta requestMeta;

	@GetMapping("/allRoutes")
	public RouteResponse getAllRoutes(){
		
		return routeService.getAllRoutes();
		
	}
	
	@GetMapping("/getById/{id}")
	public RouteResponse getRouteById(@PathVariable("id") int routeId){

		return routeService.getRouteById(routeId);
	}
	
	@PostMapping("/addOrUpdate")
	public RouteResponse addOrUpdateRoute(@RequestBody Route route){
		
		if(requestMeta != null) {
			User user = new User();
			user.setUserId(requestMeta.getUserId());
			user.setName(requestMeta.getName());
			user.setSurname(requestMeta.getSurname());
			user.setEmail(requestMeta.getEmail());
			route.setUser(user);
		}
		
		return routeService.addOrUpdateRoute(route);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public RouteResponse deleteRoute(@PathVariable("id") int routeId){

		return routeService.deleteRoute(routeId);

	}

}
