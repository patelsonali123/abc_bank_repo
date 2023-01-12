package com.usermanagement.app.repository;

import java.io.Serializable;
import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.app.entity.State;

public interface StateRepo extends JpaRepository<State, Serializable> {

	public List<State>findByCountryId(Integer countryId);
}
