/**
 * Copyright (c) 2011 - 2019, Coveo Solutions Inc.
 */
package com.coveo.challenge.cities.api;

import com.coveo.challenge.cities.usecases.SearchCitiesInput;
import com.coveo.challenge.cities.usecases.SearchCitiesUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuggestionsResourceTest
{
    private final SearchCitiesUseCase searchCitiesUseCase = mock(SearchCitiesUseCase.class);

    SuggestionsResource suggestionsResource = new SuggestionsResource(searchCitiesUseCase);

    @Test
    public void givenNoCities_itShouldNotListAny() throws Throwable
    {
        String queryString = "test";
        SearchCitiesInput input = new SearchCitiesInput(
            queryString,
            null,
            null,
            null
            );
        when(searchCitiesUseCase.searchBy(input)).thenReturn(List.of());

        String response = suggestionsResource.suggestions(queryString, null, null, null);

        Assertions.assertEquals("{\"cities\":[]}", response);
    }

    @Test
    public void givenQueryString_itShouldListCitiesContainingIt() throws Throwable
    {
        String response = suggestionsResource.suggestions("Qué", null, null, null);
        Assertions.assertEquals("{\"cities\":[{\"id\":6325494,\"name\":\"Québec\",\"ascii\":\"Quebec\",\"alt_name\":\"Bandaraya Quebec,Cathair Quebec,Cathair Québec,Ciudad ti Quebec,Ciutat de Quebec,Gorad Kvebek,Jiji la Quebec,Kebec Vile,Kebek,Kebeko,Kebeku,Kempek,Kota Quebec,Kuehbehk,Kvebek,Kvebeka,Kvebekas,Kwebek Shehiri,Kwébék Shehiri,Kébéc Vile,Lungsod ng Quebec,Lungsod ng Québec,Quebec,Quebec Ceety,Quebec City,Quebec Hiria,Quebec llaqta,Quebecborg,Quebecstad,Quebecum urbs,Québec,Québecborg,Tchubec,Thanh pho Quebec,Thành phố Québec,Vila de Quebec,Vila de Quebèc,Ville de Quebec,Ville de Québec,YQB,kbk,kh wibek,kiyupek nakaram,kui bei ke shi,kvebeka siti,kwebeg,kyubeka nagara,mdynt kybk,qwwybq syty,Κεμπέκ,Горад Квебек,Квебек,Куэбэк,קוויבק סיטי,مدينة كيبك,کبک,کیوبک شہر,क्यूबेक नगर,क्वेबेक सिटी,கியூபெக் நகரம்,ควิเบก,კვებეკი,ケベック・シティー,魁北克市,퀘벡\",\"latitude\":46.81228,\"longitude\":-71.21454,\"country\":\"CA\",\"admin1\":\"10\",\"population\":528595,\"elevation\":-1,\"tz\":\"America/Montreal\",\"modified_at\":\"2013-03-10\",\"feat_class\":\"P\",\"feat_code\":\"PPLA\",\"cc2\":\"\",\"dem\":\"54\",\"admin2\":\"\",\"admin3\":\"\",\"admin4\":\"\"}]}",
            response);
    }

    // TODO : move down the layers to the filtering domain
//    @Test
//    public void givenSearchString_itShouldAlwaysReturnTheSameCitiesForIt() throws Throwable
//    {
//        String a = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
//        String b = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
//
//        Assertions.assertEquals(b, a);
//    }
}
