package com.lms.api.food.controller;


import com.lms.api.food.dto.InputDto;
import com.lms.api.food.dto.SearchLocalRes;
import com.lms.api.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final FoodService foodService;

    @GetMapping(path = "/nsearch")
    public List<InputDto> NaverBlogSerch(@RequestParam(value = "query") String query) throws Exception {
        return foodService.NaverBlogSerch(query);

    }

    @GetMapping(path = "/lsearch")
    public SearchLocalRes localSearchList(@RequestParam(value = "query") String query)  {
        return foodService.localSearch(query);
    }


}
