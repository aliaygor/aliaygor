package com.mygradleproject.publictransportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mygradleproject.publictransportation.entity.VehicleRoute;

@Repository
public interface VehicleRouteRepository extends JpaRepository<VehicleRoute, Integer> {

}
