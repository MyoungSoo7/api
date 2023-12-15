package com.lms.api.food.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.api.food.dto.InputDto;
import com.lms.api.food.dto.SearchLocalReq;
import com.lms.api.food.dto.SearchLocalRes;
import com.lms.api.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    @GetMapping(path = "/nsearch")
    @ResponseBody
    public List<InputDto> NaverBlogSerch(@RequestParam(value = "query") String query) throws Exception {
        log.info("query>>>>>>"+query);
        List<InputDto> list =  foodService.NaverBlogSerch(query);

        return list;

    }

    @GetMapping(path = "/lsearch")
    @ResponseBody
    public SearchLocalRes localSearchList(@RequestParam(value = "query") String query) throws Exception {
        SearchLocalRes search= foodService.localSearch(query);
        return search;
    }


}
