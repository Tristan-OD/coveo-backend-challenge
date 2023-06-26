package com.coveo.challenge;

import com.github.javafaker.Faker;

import java.util.Random;

public class CityFixture {
    private Number id;
    private String name;
    private String ascii;
    private String alt_name;
    private Float latitude;
    private Float longitude;
    private String country;
    private String admin1;
    private Number population;
    private Number elevation;
    private String tz;
    private String modified_at;
    private String feat_class;
    private String feat_code;
    private String cc2;
    private String dem;
    private String admin2;
    private String admin3;
    private String admin4;

    private final Faker faker;

    public CityFixture() {
        faker = new Faker();
    }

    public CityFixture setId(Number id) {
        this.id = id;
        return this;
    }

    public CityFixture setName(String name) {
        this.name = name;
        return this;
    }

    public CityFixture setAscii(String ascii) {
        this.ascii = ascii;
        return this;
    }

    public CityFixture setAltName(String alt_name) {
        this.alt_name = alt_name;
        return this;
    }

    public CityFixture setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public CityFixture setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public CityFixture setCountry(String country) {
        this.country = country;
        return this;
    }

    public CityFixture setAdmin1(String admin1) {
        this.admin1 = admin1;
        return this;
    }

    public CityFixture setPopulation(Number population) {
        this.population = population;
        return this;
    }

    public CityFixture setElevation(Number elevation) {
        this.elevation = elevation;
        return this;
    }

    public CityFixture setTz(String tz) {
        this.tz = tz;
        return this;
    }

    public CityFixture setModifiedAt(String modified_at) {
        this.modified_at = modified_at;
        return this;
    }

    public CityFixture setFeatClass(String feat_class) {
        this.feat_class = feat_class;
        return this;
    }

    public CityFixture setFeatCode(String feat_code) {
        this.feat_code = feat_code;
        return this;
    }

    public CityFixture setCc2(String cc2) {
        this.cc2 = cc2;
        return this;
    }

    public CityFixture setDem(String dem) {
        this.dem = dem;
        return this;
    }

    public CityFixture setAdmin2(String admin2) {
        this.admin2 = admin2;
        return this;
    }

    public CityFixture setAdmin3(String admin3) {
        this.admin3 = admin3;
        return this;
    }

    public CityFixture setAdmin4(String admin4) {
        this.admin4 = admin4;
        return this;
    }

    public City build() {
        Random r = new Random();
        City city = new City();
        city.id = id != null ? id : faker.number().randomNumber();
        city.name = name != null ? name : faker.address().city();
        city.ascii = ascii != null ? ascii : faker.address().cityName();
        city.alt_name = alt_name != null ? alt_name : faker.address().cityName();
        city.latitude = latitude != null ? latitude : r.nextFloat();
        city.longitude = longitude != null ? longitude : r.nextFloat();
        city.country = country != null ? country : faker.address().countryCode();
        city.population = population != null ? population : faker.number().randomNumber();
        city.elevation = elevation != null ? elevation : faker.number().randomNumber();
        city.tz = tz != null ? tz : faker.address().timeZone();
        city.modified_at = modified_at != null ? modified_at : faker.date().toString();
        city.feat_class = feat_class != null ? feat_class : faker.letterify("?");
        city.feat_code = feat_code != null ? feat_code : faker.letterify("???");
        city.cc2 = cc2 != null ? cc2 : faker.letterify("??");
        city.dem = dem != null ? dem : faker.letterify("???");
        city.admin1 = admin1 != null ? admin1 : faker.superhero().name();
        city.admin2 = admin1 != null ? admin2 : faker.superhero().name();
        city.admin3 = admin1 != null ? admin3 : faker.superhero().name();
        city.admin4 = admin1 != null ? admin4 : faker.superhero().name();

        return city;
    }
}