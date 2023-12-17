package com.lms.api.shopping.controller;

import com.lms.api.shopping.dto.NaverShoppingSearchResultDto;
import com.lms.api.shopping.service.NaverShoppingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShoppingController {

    private final NaverShoppingService naverShoppingService;

    @GetMapping(path="api/shopping")
    @ResponseBody
    public List<NaverShoppingSearchResultDto> getShoppingItems(@RequestParam(value="query") String query) throws IOException {

        if(StringUtil.isBlank(query)){
            query = "커피";
        }
        List<NaverShoppingSearchResultDto> naverShoppingSearchResultDtoList = naverShoppingService.getItems(query);
        return naverShoppingSearchResultDtoList;
    }



}
