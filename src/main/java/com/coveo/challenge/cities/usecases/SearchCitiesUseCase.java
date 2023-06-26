package com.coveo.challenge.cities.usecases;


import com.coveo.challenge.City;
import com.coveo.challenge.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchCitiesUseCase {
    @Autowired
    private CsvParser csvParser;

    public SearchCitiesOutput searchBy(SearchCitiesInput input) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<City> cities = new ArrayList<>(List.copyOf(csvParser.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv")).values()));
        cities.removeIf(c -> !c.name.contains(input.getQueryString()));

        Double latitude = input.getLatitude();
        if (latitude != null) {
            cities.removeIf(city -> Math.abs(city.latitude - latitude) > 10);
        }

        Double longitude = input.getLongitude();
        if (longitude != null) {
            cities.removeIf(city -> Math.abs(city.longitude - longitude) > 20);
        }

        Integer page = input.getPage();
        if (page != null) {
            int totalNumberOfPages = cities.size() % 5 == 0 ? cities.size() / 5 : (cities.size() / 5) + 1;
            // TOOD : induces bug when first page is requested
            if (page < (int) totalNumberOfPages) {
                return new SearchCitiesOutput(
                    cities.subList((page * 5), (page * 5 + 5) >= cities.size() ? cities.size() : page * 5 + 5),
                    totalNumberOfPages
                );
            } else {
                return new SearchCitiesOutput(List.of(), null);
            }
        }

        return new SearchCitiesOutput(cities, null);
    }
}
