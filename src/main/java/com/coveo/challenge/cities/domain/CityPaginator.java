package com.coveo.challenge.cities.domain;

import com.coveo.challenge.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityPaginator {
    public List<City> takePageFrom(int page, List<City> cities) {
        // TOOD : induces bug when first page is requested
        if (page < (int) totalNumberOfPagesIn(cities)) {
            return cities.subList((page * 5), (page * 5 + 5) >= cities.size() ? cities.size() : page * 5 + 5);
        }

        return List.of();
    }

    public int totalNumberOfPagesIn(List<City> cities) {
        return cities.size() % 5 == 0 ? cities.size() / 5 : (cities.size() / 5) + 1;
    }
}
