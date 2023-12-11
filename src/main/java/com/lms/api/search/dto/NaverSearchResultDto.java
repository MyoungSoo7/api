package com.lms.api.search.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverSearchResultDto {

    private String news;
    @Override
    public String toString() {
        return "NaverSearchResultDto{" +
                "news='" + news + '\'' +
                '}';
    }
}
