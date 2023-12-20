package com.lms.api.food.dto;

import lombok.Data;

@Data
public class FoodInputDto {

    private String food;
    private String sort;
    private String page;
    private String cnt;
    private String keyword;
}
