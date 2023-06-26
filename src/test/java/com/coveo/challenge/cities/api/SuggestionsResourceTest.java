/**
 * Copyright (c) 2011 - 2019, Coveo Solutions Inc.
 */
package com.coveo.challenge.cities.api;

import com.coveo.challenge.City;
import com.coveo.challenge.CityFixture;
import com.coveo.challenge.cities.usecases.SearchCitiesInput;
import com.coveo.challenge.cities.usecases.SearchCitiesUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuggestionsResourceTest {
    private final SearchCitiesUseCase searchCitiesUseCase = mock(SearchCitiesUseCase.class);

    SuggestionsResource suggestionsResource = new SuggestionsResource(searchCitiesUseCase);

    @Test
    public void givenNoCities_itShouldNotListAny() throws Throwable {
        String queryString = "test";
        when(searchCitiesUseCase.searchBy(any())).thenReturn(List.of());

        String response = suggestionsResource.suggestions(queryString, null, null, null);

        assertEquals("{\"cities\":[]}", response);
    }

    @Test
    public void givenParameters_itShouldSearchForCitiesWithThem() throws Throwable {
        City city = givenCity();
        ArgumentCaptor<SearchCitiesInput> input = ArgumentCaptor.forClass(SearchCitiesInput.class);
        when(searchCitiesUseCase.searchBy(input.capture())).thenReturn(List.of(city));

        String response = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);

        SearchCitiesInput capturedInput = input.getValue();
        assertEquals(capturedInput.getQueryString(), "Qué");
        assertEquals(capturedInput.getLatitude(), 43.0);
        assertEquals(capturedInput.getLongitude(), -23.2);
        assertEquals(capturedInput.getPage(), null);
        assertEquals("{\"cities\":[{\"id\":6325494,\"name\":\"Québec\",\"ascii\":\"Quebec\",\"alt_name\":\"Bandaraya Quebec,Cathair Quebec,Cathair Québec,Ciudad ti Quebec,Ciutat de Quebec,Gorad Kvebek,Jiji la Quebec,Kebec Vile,Kebek,Kebeko,Kebeku,Kempek,Kota Quebec,Kuehbehk,Kvebek,Kvebeka,Kvebekas,Kwebek Shehiri,Kwébék Shehiri,Kébéc Vile,Lungsod ng Quebec,Lungsod ng Québec,Quebec,Quebec Ceety,Quebec City,Quebec Hiria,Quebec llaqta,Quebecborg,Quebecstad,Quebecum urbs,Québec,Québecborg,Tchubec,Thanh pho Quebec,Thành phố Québec,Vila de Quebec,Vila de Quebèc,Ville de Quebec,Ville de Québec,YQB,kbk,kh wibek,kiyupek nakaram,kui bei ke shi,kvebeka siti,kwebeg,kyubeka nagara,mdynt kybk,qwwybq syty,Κεμπέκ,Горад Квебек,Квебек,Куэбэк,קוויבק סיטי,مدينة كيبك,کبک,کیوبک شہر,क्यूबेक नगर,क्वेबेक सिटी,கியூபெக் நகரம்,ควิเบก,კვებეკი,ケベック・シティー,魁北克市,퀘벡\",\"latitude\":46.81228,\"longitude\":-71.21454,\"country\":\"CA\",\"admin1\":\"10\",\"population\":528595,\"elevation\":-1,\"tz\":\"America/Montreal\",\"modified_at\":\"2013-03-10\",\"feat_class\":\"P\",\"feat_code\":\"PPLA\",\"cc2\":\"\",\"dem\":\"54\",\"admin2\":\"\",\"admin3\":\"\",\"admin4\":\"\"}]}", response);
    }

    private static City givenCity() {
        return new CityFixture()
            .setId(6325494)
            .setName("Québec")
            .setAscii("Quebec")
            .setAltName("Bandaraya Quebec,Cathair Quebec,Cathair Québec,Ciudad ti Quebec,Ciutat de Quebec,Gorad Kvebek,Jiji la Quebec,Kebec Vile,Kebek,Kebeko,Kebeku,Kempek,Kota Quebec,Kuehbehk,Kvebek,Kvebeka,Kvebekas,Kwebek Shehiri,Kwébék Shehiri,Kébéc Vile,Lungsod ng Quebec,Lungsod ng Québec,Quebec,Quebec Ceety,Quebec City,Quebec Hiria,Quebec llaqta,Quebecborg,Quebecstad,Quebecum urbs,Québec,Québecborg,Tchubec,Thanh pho Quebec,Thành phố Québec,Vila de Quebec,Vila de Quebèc,Ville de Quebec,Ville de Québec,YQB,kbk,kh wibek,kiyupek nakaram,kui bei ke shi,kvebeka siti,kwebeg,kyubeka nagara,mdynt kybk,qwwybq syty,Κεμπέκ,Горад Квебек,Квебек,Куэбэк,קוויבק סיטי,مدينة كيبك,کبک,کیوبک شہر,क्यूबेक नगर,क्वेबेक सिटी,கியூபெக் நகரம்,ควิเบก,კვებეკი,ケベック・シティー,魁北克市,퀘벡")
            .setLatitude(46.81228f)
            .setLongitude(-71.21454f)
            .setCountry("CA")
            .setPopulation(528595)
            .setElevation(-1)
            .setTz("America/Montreal")
            .setModifiedAt("2013-03-10")
            .setFeatClass("P")
            .setFeatCode("PPLA")
            .setDem("54")
            .setCc2("")
            .setAdmin1("10")
            .setAdmin2("")
            .setAdmin3("")
            .setAdmin4("")
            .build();
    }

    // TODO : move down the layers to the filtering domain
//    @Test
//    public void givenQueryString_itShouldListCitiesContainingIt() throws Throwable
//    {
//        String response = suggestionsResource.suggestions("Qué", null, null, null);
//        Assertions.assertEquals("{\"cities\":[{\"id\":6325494,\"name\":\"Québec\",\"ascii\":\"Quebec\",\"alt_name\":\"Bandaraya Quebec,Cathair Quebec,Cathair Québec,Ciudad ti Quebec,Ciutat de Quebec,Gorad Kvebek,Jiji la Quebec,Kebec Vile,Kebek,Kebeko,Kebeku,Kempek,Kota Quebec,Kuehbehk,Kvebek,Kvebeka,Kvebekas,Kwebek Shehiri,Kwébék Shehiri,Kébéc Vile,Lungsod ng Quebec,Lungsod ng Québec,Quebec,Quebec Ceety,Quebec City,Quebec Hiria,Quebec llaqta,Quebecborg,Quebecstad,Quebecum urbs,Québec,Québecborg,Tchubec,Thanh pho Quebec,Thành phố Québec,Vila de Quebec,Vila de Quebèc,Ville de Quebec,Ville de Québec,YQB,kbk,kh wibek,kiyupek nakaram,kui bei ke shi,kvebeka siti,kwebeg,kyubeka nagara,mdynt kybk,qwwybq syty,Κεμπέκ,Горад Квебек,Квебек,Куэбэк,קוויבק סיטי,مدينة كيبك,کبک,کیوبک شہر,क्यूबेक नगर,क्वेबेक सिटी,கியூபெக் நகரம்,ควิเบก,კვებეკი,ケベック・シティー,魁北克市,퀘벡\",\"latitude\":46.81228,\"longitude\":-71.21454,\"country\":\"CA\",\"admin1\":\"10\",\"population\":528595,\"elevation\":-1,\"tz\":\"America/Montreal\",\"modified_at\":\"2013-03-10\",\"feat_class\":\"P\",\"feat_code\":\"PPLA\",\"cc2\":\"\",\"dem\":\"54\",\"admin2\":\"\",\"admin3\":\"\",\"admin4\":\"\"}]}",
//            response);
//    }
//
//    @Test
//    public void givenSearchString_itShouldAlwaysReturnTheSameCitiesForIt() throws Throwable
//    {
//        String a = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
//        String b = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
//
//        Assertions.assertEquals(b, a);
//    }
}
