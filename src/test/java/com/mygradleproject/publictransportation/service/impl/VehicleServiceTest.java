package com.mygradleproject.publictransportation.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.entity.Vehicle;
import com.mygradleproject.publictransportation.repository.VehicleRepository;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {
	@Mock
	private VehicleRepository vehicleRepository;
	
	@Test
	public void testSave() {
		
		User user = new User();
		user.setUserId(1);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("Test-Brand");
		vehicle.setPlate("Test-Plate");
		vehicle.setModelYear(2022);
		vehicle.setTotalNumberPassenger(5);
		vehicle.setUser(user);
		
		vehicleRepository.save(vehicle);
		
		Assertions.assertThat(!vehicle.getPlate().equals(null) && !vehicle.getBrand().equals(null));
		
	}


}
