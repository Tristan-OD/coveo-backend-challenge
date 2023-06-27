package com.coveo.challenge.cities.api;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.coveo.challenge.cities.usecases.SearchCitiesInput;
import com.coveo.challenge.cities.usecases.SearchCitiesOutput;
import com.coveo.challenge.cities.usecases.SearchCitiesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SuggestionsResource {

    private final SearchCitiesUseCase searchCitiesUseCase;

    public SuggestionsResource(SearchCitiesUseCase searchCitiesUseCase) {
        this.searchCitiesUseCase = searchCitiesUseCase;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/suggestions")
    public ResponseEntity<String> suggestions(
        @RequestParam String q,
        @RequestParam(defaultValue = "45.9778182", required = false) Double latitude,
        @RequestParam(defaultValue = "-77.8968753", required = false) Double longitude,
        @RequestParam(required = false) Integer page
    ) throws Throwable {
        if (q == null) {
            String message = "Required query parameter q (query string), is missing";
            // TODO : spring boot configuration does not display message for some reason
            return ResponseEntity.badRequest().body(message);
        }

        System.out.println(new Date() + " --- Entering suggestions endpoint parameters are: q=" + q + ", latitude="
            + latitude + ", longitude=" + longitude);

        Map<String, Object> results = new HashMap<>();
        SearchCitiesInput input = new SearchCitiesInput(q, latitude, longitude, page);
        SearchCitiesOutput output = searchCitiesUseCase.searchBy(input);

        if (page != null) {
            results.put("page", page);
            results.put("totalNumberOfPages", output.getTotalNumberOfPages());
        }

        results.put("cities", output.getCities());

        String body = new ObjectMapper().writeValueAsString(results);
        return ResponseEntity.ok(body);
    }
}
