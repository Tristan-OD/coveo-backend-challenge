package com.coveo.challenge.cities.infrastructure;

import com.coveo.challenge.cities.domain.City;
import com.coveo.challenge.cities.domain.CitiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;

@Repository
public class CsvCitiesDao implements CitiesDao {
    @Autowired
    private CsvParser csvParser;

    @Override
    public List<City> listAll() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new ArrayList<>(copyOf(csvParser.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv")).values()));
    }
}
