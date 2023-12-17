package com.lms.api.food.dto;


import lombok.Data;

@Data
public class LocalSearchResultDto {

    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;
}
