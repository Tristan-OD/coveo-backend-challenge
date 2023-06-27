package com.coveo.challenge.cities.domain;

import com.coveo.challenge.CityFixture;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CityPaginatorTest {

    private final CityPaginator paginator = new CityPaginator();

    @Test
    public void givenNoCities_itShouldNotReturnAnyInForAnyPage() {
        int page = 1;

        List<City> pagedCities = paginator.takePageFrom(page, List.of());

        assertEquals(pagedCities.size(), 0);
    }

    @Test
    public void givenLessCitiesThanPageMax_itShouldReturnThemInFirstPage() {
        int page = 1;
        City city = new CityFixture().build();
        City anotherCity = new CityFixture().build();
        City yetAnotherCity = new CityFixture().build();
        City fourthCity = new CityFixture().build();

        List<City> pagedCities = paginator.takePageFrom(page, List.of(city, anotherCity, yetAnotherCity, fourthCity));

        assertEquals(pagedCities.size(), 4);
        assertEquals(pagedCities.get(0), city);
        assertEquals(pagedCities.get(1), anotherCity);
        assertEquals(pagedCities.get(2), yetAnotherCity);
        assertEquals(pagedCities.get(3), fourthCity);
    }

    @Test
    public void givenCitiesHaveExactNumberOfPages_itShouldReturnTheRightPage() {
        int page = 2;
        List<City> cities = givenCities(10);

        List<City> pagedCities = paginator.takePageFrom(page, cities);

        assertEquals(5, pagedCities.size());
        assertEquals(cities.get(5), pagedCities.get(0));
        assertEquals(cities.get(6), pagedCities.get(1));
        assertEquals(cities.get(7), pagedCities.get(2));
        assertEquals(cities.get(8), pagedCities.get(3));
        assertEquals(cities.get(9), pagedCities.get(4));
    }

    @Test
    public void givenMoreCitiesThanAPage_itCanGetTheFirstPage() {
        int page = 1;
        List<City> cities = givenCities(6);

        List<City> pagedCities = paginator.takePageFrom(page, cities);

        assertEquals(5, pagedCities.size());
        assertEquals(cities.get(0), pagedCities.get(0));
        assertEquals(cities.get(1), pagedCities.get(1));
        assertEquals(cities.get(2), pagedCities.get(2));
        assertEquals(cities.get(3), pagedCities.get(3));
        assertEquals(cities.get(4), pagedCities.get(4));
    }

    @Test
    public void itCanGetTheLastPage() {
        int page = 2;
        List<City> cities = givenCities(8);

        List<City> pagedCities = paginator.takePageFrom(page, cities);

        assertEquals(3, pagedCities.size());
        assertEquals(cities.get(5), pagedCities.get(0));
        assertEquals(cities.get(6), pagedCities.get(1));
        assertEquals(cities.get(7), pagedCities.get(2));
    }

    @Test
    public void givenMoreCitiesThanAPage_itShouldSetTotalPagesToOneMore() {
        List<City> cities = givenCities(8);

        int numberOfPAges = paginator.totalNumberOfPagesIn(cities);

        assertEquals(2, numberOfPAges);
    }

    @Test
    public void givenLessCitiesThanAPages_itShouldStillSetTotalToOne() {
        List<City> cities = givenCities(3);

        int numberOfPAges = paginator.totalNumberOfPagesIn(cities);

        assertEquals(1, numberOfPAges);
    }

    @Test
    public void givenCitiesInExactNumberOfPages_itShouldReturnThatNumber() {
        List<City> cities = givenCities(15);

        int numberOfPAges = paginator.totalNumberOfPagesIn(cities);

        assertEquals(3, numberOfPAges);
    }

    private List<City> givenCities(int number) {
        return Stream
            .generate(() -> new CityFixture().build())
            .limit(number)
            .collect(Collectors.toList());
    }
}