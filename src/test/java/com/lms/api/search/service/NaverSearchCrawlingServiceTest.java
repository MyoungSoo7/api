package com.lms.api.search.service;

import com.lms.api.search.dto.NaverSearchResultDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Service
class NaverSearchCrawlingServiceTest {

    @Test
    @DisplayName("네이버 검색 결과 가져오기")
    void getNaverSearchResult() throws IOException {
        //given
        String query = "개발자";
        String URL = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=" + query;
        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.select("div.news_contents > a");
        //when
        List<NaverSearchResultDto> naverSearchResultDtoList = new ArrayList<NaverSearchResultDto>();
        //then
        for(Element element : elements){
            NaverSearchResultDto naverSearchResultDto = new NaverSearchResultDto();
            if(!element.text().equals("")&& !element.text().equals("동영상")){
                naverSearchResultDto.setNews(element.text());
                naverSearchResultDtoList.add (naverSearchResultDto);
            }
        }

        for(NaverSearchResultDto item : naverSearchResultDtoList){
            System.out.println("item=>"+ item.toString());
        }
        assertEquals(10, naverSearchResultDtoList.size());
        //System.out.println();



    }

}