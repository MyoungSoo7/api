package com.lms.api.shopping.controller;

import com.lms.api.shopping.dto.NaverShoppingSearchResultDto;
import com.lms.api.shopping.service.NaverShoppingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShoppingController {

    private final NaverShoppingService naverShoppingService;

    @GetMapping("/api/shopping-item")
    @ResponseBody
    public List<NaverShoppingSearchResultDto> getShoppingItems(@RequestParam(value="query") String query) throws IOException {

        if(StringUtil.isBlank(query)){
            query = "커피";
        }
        List<NaverShoppingSearchResultDto> naverShoppingSearchResultDtoList = naverShoppingService.getItems(query);
        for(NaverShoppingSearchResultDto naverShoppingSearchResultDto : naverShoppingSearchResultDtoList){
            log.info("itemDto=>"+ naverShoppingSearchResultDto.toString());
        }
        return naverShoppingSearchResultDtoList;
    }

}
