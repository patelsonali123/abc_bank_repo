 package com.usermanagement.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.app.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Serializable>{

}
