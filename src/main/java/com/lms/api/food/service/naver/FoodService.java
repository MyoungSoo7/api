package com.lms.api.food.service.naver;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.api.food.dto.naver.*;
import com.lms.api.food.entity.Food;
import com.lms.api.food.repository.FoodReposiroty;
import com.lms.api.food.service.FoodRepositoryService;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodService {

    @Value(value = "${naver.client.id}")
    private String naverClientId;
    @Value(value = "${naver.client.secret}")
    private String naverClientSecret;
    private final FoodRepositoryService foodRepositoryService;

    public SearchLocalRes localSearch(String query ,String searchUrl ,String sort)  throws IOException {
        SearchLocalReq req =new SearchLocalReq();
        req.setQuery(query);
        req.setSort(sort);

        var uri = UriComponentsBuilder
                .fromUriString(searchUrl)
                .queryParams(req.toMultiValueMap())
                .build()
                .encode()
                .toUri();

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


}
