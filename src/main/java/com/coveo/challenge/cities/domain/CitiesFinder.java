package com.coveo.challenge.cities.domain;

import com.coveo.challenge.City;
import com.coveo.challenge.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesFinder {
    @Autowired
    private CsvParser csvParser;

    public List<City> findBy(String queryString, Double latitude, Double longitude) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<City> cities = new ArrayList<>(List.copyOf(csvParser.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv")).values()));
        cities.removeIf(c -> !c.name.contains(queryString));

        if (latitude != null) {
            cities.removeIf(city -> Math.abs(city.latitude - latitude) > 10);
        }

        if (longitude != null) {
            cities.removeIf(city -> Math.abs(city.longitude - longitude) > 20);
        }

        return cities;
    }
}
