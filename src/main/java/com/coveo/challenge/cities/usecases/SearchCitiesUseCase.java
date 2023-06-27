package com.coveo.challenge.cities.usecases;


import com.coveo.challenge.cities.domain.City;
import com.coveo.challenge.cities.domain.CitiesFinder;
import com.coveo.challenge.cities.domain.CityPaginator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCitiesUseCase {
    private final CitiesFinder citiesFinder;
    private final CityPaginator paginator;

    public SearchCitiesUseCase(CitiesFinder citiesFinder, CityPaginator paginator) {
        this.citiesFinder = citiesFinder;
        this.paginator = paginator;
    }

    public SearchCitiesOutput searchBy(SearchCitiesInput input) {
        List<City> cities = citiesFinder.findBy(input.getQueryString(), input.getLatitude(), input.getLongitude());

        Integer page = input.getPage();
        if (page != null) {
            List<City> paginated = paginator.takePageFrom(page, cities);
            int totalNumberOfPages = paginator.totalNumberOfPagesIn(cities);

            return new SearchCitiesOutput(paginated, totalNumberOfPages);
        }

        return new SearchCitiesOutput(cities, null);
    }
}
