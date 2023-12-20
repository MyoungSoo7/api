package com.lms.api.food.service.naver;


import com.lms.api.food.dto.naver.SearchLocalRes;
import com.lms.api.food.entity.Food;
import com.lms.api.food.service.FoodRepositoryService;
import com.lms.api.food.service.kakao.CategorySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodService {

    @Value(value = "${naver.client.id}")
    private String naverClientId;
    @Value(value = "${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;
    private final FoodRepositoryService foodRepositoryService;

    @CircuitBreaker(name = "circuit-sample-3000", fallbackMethod = "searchFoodFallback")
    public SearchLocalRes localSearch(String query   ,String sort)  throws Exception {

        saveFoodKeyword(query);

        long start =System.currentTimeMillis();
        Thread.sleep(5000L);
        long end =System.currentTimeMillis();
        log.info("[slowCall] call => {}ms", end - start);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(naverLocalSearchUrl);
        uriBuilder.queryParam("query", query);
        uriBuilder.queryParam("display", "5");
        uriBuilder.queryParam("start", "1");
        uriBuilder.queryParam("sort", sort);
        URI uri = uriBuilder.build().encode().toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};
        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        String responseBody = String.valueOf(responseEntity.getBody());
        SearchLocalRes searchLocalRes = responseEntity.getBody();

        return searchLocalRes;
    }

    private Food saveFoodKeyword(String query) {
        return foodRepositoryService.save(Food.builder().food(query).build());
    }

    private SearchLocalRes searchFoodFallback(Throwable t) {
        log.info("[fallbackMethod] call!!");
        SearchLocalRes sr = null;
        return sr;
    }

}
