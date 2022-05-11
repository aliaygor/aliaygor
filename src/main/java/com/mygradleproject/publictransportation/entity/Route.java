package com.mygradleproject.publictransportation.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "start_station", referencedColumnName = "id")
	private Station startStation;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "finish_station", referencedColumnName = "id")
	private Station finishStation;
	@Column(name="route_num")
	private int routeNum;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "create_user_id", referencedColumnName = "id")
	private User user;
	@Column(name="create_date")
	private Date createDate;
	
	public Route() {
		super();
	}

	public Route(int id, Station startStation, Station finishStation, int routeNum, User user, Date createDate) {
		super();
		this.id = id;
		this.startStation = startStation;
		this.finishStation = finishStation;
		this.routeNum = routeNum;
		this.user = user;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getFinishStation() {
		return finishStation;
	}

	public void setFinishStation(Station finishStation) {
		this.finishStation = finishStation;
	}

	public int getRouteNum() {
		return routeNum;
	}

	public void setRouteNum(int routeNum) {
		this.routeNum = routeNum;
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
