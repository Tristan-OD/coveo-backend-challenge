package com.coveo.challenge.cities.infrastructure;

import com.coveo.challenge.City;
import com.coveo.challenge.CsvParser;
import com.coveo.challenge.cities.domain.CitiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;

@Component
public class CsvCitiesDao implements CitiesDao {
    @Autowired
    private CsvParser csvParser;

    @Override
    public List<City> listAll() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new ArrayList<>(copyOf(csvParser.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv")).values()));
    }
}
