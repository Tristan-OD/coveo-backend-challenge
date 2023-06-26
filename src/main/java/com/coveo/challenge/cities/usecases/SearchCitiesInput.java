package com.coveo.challenge.cities.usecases;

public class SearchCitiesInput {
    private final String queryString;
    private final Integer page;
    private final Double latitude;
    private final Double longitude;

    public SearchCitiesInput(String queryString, Double latitude, Double longitude, Integer page) {
        this.queryString = queryString;
        this.page = page;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getQueryString() {
        return queryString;
    }

    public Integer getPage() {
        return page;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
