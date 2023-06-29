package com.coveo.challenge.cities.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityPaginator {
    public List<City> takePageFrom(int page, List<City> cities) {
        if (page <= totalNumberOfPagesIn(cities)) {
            int pageStart = (page - 1) * 5;
            int pageEnd = Math.min((page * 5), cities.size());
            return cities.subList(pageStart, pageEnd);
        }

        return cities;
    }

    public int totalNumberOfPagesIn(List<City> cities) {
        return cities.size() % 5 == 0 ? cities.size() / 5 : (cities.size() / 5) + 1;
    }
}
