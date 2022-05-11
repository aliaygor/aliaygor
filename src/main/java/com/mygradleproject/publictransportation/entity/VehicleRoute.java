package com.mygradleproject.publictransportation.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_route")
public class VehicleRoute{

	@Id
	@Column(name="id")
	private int id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	private Vehicle vehicle;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "route_id", referencedColumnName = "id")
	private Route route;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "create_user_id", referencedColumnName = "id")
	private User user;
	@Column(name="create_date")
	private Date createDate;
	
	public VehicleRoute() {
		super();
	}
	
	public VehicleRoute(int id, Vehicle vehicle, Route route, User user, Date createDate) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.route = route;
		this.user = user;
		this.createDate = createDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}
