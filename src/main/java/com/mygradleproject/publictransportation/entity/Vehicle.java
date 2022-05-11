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
@Table(name = "vehicle")
public class Vehicle {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="brand")
	private String brand;
	@Column(name="plate")
	private String plate;
	@Column(name="total_passenger")
	private int totalNumberPassenger;
	@Column(name="model_year")
	private int modelYear;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "create_user_id", referencedColumnName = "id")
	private User user;
	@Column(name="create_date")
	private Date createDate;
	
	public Vehicle() {
		super();
	}

	public Vehicle(int id, String brand, String plate, int totalNumberPassenger, int modelYear, User user,
			Date createDate) {
		super();
		this.id = id;
		this.brand = brand;
		this.plate = plate;
		this.totalNumberPassenger = totalNumberPassenger;
		this.modelYear = modelYear;
		this.user = user;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getTotalNumberPassenger() {
		return totalNumberPassenger;
	}

	public void setTotalNumberPassenger(int totalNumberPassenger) {
		this.totalNumberPassenger = totalNumberPassenger;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
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
