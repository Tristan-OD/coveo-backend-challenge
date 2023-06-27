package com.coveo.challenge.cities.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesDao {
    List<City> listAll();
}
