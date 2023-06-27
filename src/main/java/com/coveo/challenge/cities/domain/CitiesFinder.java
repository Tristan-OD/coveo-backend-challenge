package com.coveo.challenge.cities.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CitiesFinder {
    private final CitiesDao citiesDao;


    public CitiesFinder(CitiesDao citiesDao) {
        this.citiesDao = citiesDao;
    }

    public List<City> findBy(String queryString, Double latitude, Double longitude) {
        List<City> cities = citiesDao.listAll();

        if (cities.isEmpty()) {
            return List.of();
        }

        List<City> matching = cities.stream()
            .filter(c -> c.name.contains(queryString))
            .collect(Collectors.toList());

        if (latitude != null) {
            matching.removeIf(city -> Math.abs(city.latitude - latitude) > 10);
        }

        if (longitude != null) {
            matching.removeIf(city -> Math.abs(city.longitude - longitude) > 20);
        }

        return matching;
    }
}
