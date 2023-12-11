package com.lms.api.search.service;

import com.lms.api.search.dto.NaverSearchResultDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NaverSearchCrawlingService {

    public List<NaverSearchResultDto> getNaverSearchResult(String query) throws IOException {

        String URL = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=" + query;
        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.select("div.news_contents > a");

        List<NaverSearchResultDto> naverSearchResultDtoList = new ArrayList<NaverSearchResultDto>();

        for(Element element : elements){
            NaverSearchResultDto naverSearchResultDto = new NaverSearchResultDto();
            if(!element.text().equals("")&& !element.text().equals("동영상")){
                naverSearchResultDto.setNews(element.text());
                naverSearchResultDtoList.add (naverSearchResultDto);
            }

        }
        return naverSearchResultDtoList;
    }
}
