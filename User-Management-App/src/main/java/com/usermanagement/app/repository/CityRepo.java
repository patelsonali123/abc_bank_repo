package com.usermanagement.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.app.entity.Cities;

public interface CityRepo extends JpaRepository<CityRepo, Serializable>{

	public List<Cities> finByStateId(Integer stateId);
}
