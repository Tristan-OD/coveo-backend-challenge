package com.coveo.challenge.cities.usecases;

import com.coveo.challenge.cities.domain.City;
import com.coveo.challenge.CityFixture;
import com.coveo.challenge.cities.domain.CitiesFinder;
import com.coveo.challenge.cities.domain.CityPaginator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchCitiesUseCaseTest {
    private final CitiesFinder citiesFinder = mock(CitiesFinder.class);
    private final CityPaginator cityPaginator = mock(CityPaginator.class);
    private final SearchCitiesUseCase useCase = new SearchCitiesUseCase(citiesFinder, cityPaginator);

    @Test
    public void givenNoCitiesFound_itShouldNotListAny() {
        SearchCitiesInput input = new SearchCitiesInput("A query string", null, null, null);
        when(citiesFinder.findBy(input.getQueryString(), input.getLatitude(), input.getLongitude())).thenReturn(List.of());

        SearchCitiesOutput output = useCase.searchBy(input);

        assertEquals(output.getCities(), List.of());
        assertNull(output.getTotalNumberOfPages());
    }

    @Test
    public void itShouldListFoundCities() {
        SearchCitiesInput input = new SearchCitiesInput("A query string", 40d, -70d, null);
        City city = new CityFixture().build();
        City anotherCity = new CityFixture().build();
        when(citiesFinder.findBy(input.getQueryString(), input.getLatitude(), input.getLongitude())).thenReturn(List.of(city, anotherCity));

        SearchCitiesOutput output = useCase.searchBy(input);

        assertEquals(output.getCities(), List.of(city, anotherCity));
        assertNull(output.getTotalNumberOfPages());
    }

    @Test
    public void givenPage_itShouldListCitiesOfThatPage() {
        int page = 2;
        SearchCitiesInput input = new SearchCitiesInput("A query string", 40d, -70d, page);
        City city = new CityFixture().build();
        City anotherCity = new CityFixture().build();
        List<City> cities = List.of(city, anotherCity);
        when(citiesFinder.findBy(input.getQueryString(), input.getLatitude(), input.getLongitude())).thenReturn(List.of(city, anotherCity));
        List<City> paginatedCities = List.of(anotherCity);
        when(cityPaginator.takePageFrom(page, cities)).thenReturn(paginatedCities);
        when(cityPaginator.totalNumberOfPagesIn(cities)).thenReturn(2);

        SearchCitiesOutput output = useCase.searchBy(input);

        assertEquals(output.getCities(), paginatedCities);
        assertEquals(output.getTotalNumberOfPages(), 2);
    }
}