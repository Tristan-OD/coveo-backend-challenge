package com.coveo.challenge.cities.domain;


import com.coveo.challenge.City;
import com.coveo.challenge.CityFixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CitiesFinderTest {
    CitiesDao citiesDao = mock(CitiesDao.class);
    CitiesFinder finder = new CitiesFinder(citiesDao);

    @Test
    public void givenNoCities_itShouldNotFindAny() {
        when(citiesDao.listAll()).thenReturn(List.of());

        List<City> cities = finder.findBy("A query string", null, null);

        assertEquals(cities.size(), 0);
    }

    @Test
    public void givenNoCityNamesContainQueryString_itShouldNotFindAny() {
        City city = new CityFixture().setName("Does not contain").build();
        when(citiesDao.listAll()).thenReturn(List.of(city));

        List<City> cities = finder.findBy("name", null, null);

        assertEquals(cities.size(), 0);
    }

    @Test
    public void givenCitiesWithMatchingNameButOutsideLatitudeDistance_theyShouldNotBeFound() {
        float oneOutsideAllowedDistance = 29f;
        City city = new CityFixture()
            .setName("Contains name")
            .setLatitude(oneOutsideAllowedDistance)
            .build();
        when(citiesDao.listAll()).thenReturn(List.of(city));

        List<City> cities = finder.findBy("name", 40d, null);

        assertEquals(cities.size(), 0);
    }

    @Test
    public void givenCitiesWithMatchingNameButOutsideLongitudeDistance_theyShouldNotBeFound() {
        float oneOutsideAllowedDistance = 51f;
        City city = new CityFixture()
            .setName("Contains name")
            .setLongitude(oneOutsideAllowedDistance)
            .build();
        when(citiesDao.listAll()).thenReturn(List.of(city));

        List<City> cities = finder.findBy("name", null, 30d);

        assertEquals(cities.size(), 0);
    }

    @Test
    public void givenCitiesInAllowedDistanceWithMatchingName_theyShouldBeFound() {
        float insideAllowedDistance = 50f;
        City city = new CityFixture()
            .setName("Contains name")
            .setLongitude(insideAllowedDistance)
            .setLatitude(insideAllowedDistance)
            .build();
        City anotherCity = new CityFixture()
            .setName("name is contained")
            .setLongitude(insideAllowedDistance)
            .setLatitude(insideAllowedDistance)
            .build();
        when(citiesDao.listAll()).thenReturn(List.of(city, anotherCity));

        List<City> cities = finder.findBy("name", 40d, 30d);

        assertSame(cities.get(0), city);
        assertSame(cities.get(1), anotherCity);
    }
}