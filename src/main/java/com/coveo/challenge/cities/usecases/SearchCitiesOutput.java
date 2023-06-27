package com.coveo.challenge.cities.usecases;

import com.coveo.challenge.cities.domain.City;

import java.util.List;

public class SearchCitiesOutput {
    private final List<City> cities;
    private final Integer totalNumberOfPages;

    public SearchCitiesOutput(List<City> cities, Integer totalNumberOfPages) {
        this.cities = cities;
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public List<City> getCities() {
        return cities;
    }

    public Integer getTotalNumberOfPages() {
        return totalNumberOfPages;
    }
}
