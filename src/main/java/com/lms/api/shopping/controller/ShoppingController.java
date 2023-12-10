package com.lms.api.shopping.controller;

import com.lms.api.search.dto.NaverShoppingSearchResultDto;
import com.lms.api.search.dto.NaverSearchResultDto;
import com.lms.api.search.service.NaverShoppingService;
import com.lms.api.search.service.NaverSearchCrawlingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final NaverShoppingService naverShoppingService;

    @GetMapping("/api/shopping-item")
    @ResponseBody
    public void getShoppingItems(@RequestParam String query) throws IOException {
        //String query ="커피";
        List<NaverShoppingSearchResultDto> naverShoppingSearchResultDtoList = naverShoppingService.getItems(query);
        log.info("itemDtoList=>"+ naverShoppingSearchResultDtoList.toString());

    }

}
