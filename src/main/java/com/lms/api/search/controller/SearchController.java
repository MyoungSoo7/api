package com.lms.api.search.controller;

import com.lms.api.search.dto.NaverSearchResultDto;
import com.lms.api.search.service.NaverSearchCrawlingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final NaverSearchCrawlingService naverSearchCrawlingService;

    @GetMapping("/api/news")
    @ResponseBody
    public List<NaverSearchResultDto> getNaverSearchCrawlingService(@RequestParam(value = "query") String query) throws IOException {
        if(StringUtil.isBlank(query)){
            query = "개발자";
        };
        
        List<NaverSearchResultDto> itemDtoList = naverSearchCrawlingService.getNaverSearchResult(query);

        for(NaverSearchResultDto item : itemDtoList){
            log.info("item=>"+ item.toString());
        }

        return itemDtoList;
    }


}
