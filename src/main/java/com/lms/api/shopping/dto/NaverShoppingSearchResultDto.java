package com.lms.api.search.dto;

import lombok.Getter;

@Getter
public class NaverShoppingSearchResultDto {

    private String title;
    private String link;
    private String image;
    private int lprice;

    @Override
    public String toString() {
        return "ItemDto{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", lprice=" + lprice +
                '}';
    }
}
