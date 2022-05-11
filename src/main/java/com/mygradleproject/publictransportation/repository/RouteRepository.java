package com.mygradleproject.publictransportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mygradleproject.publictransportation.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

}
