package com.lms.api.food.dto.kakao;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class DocumentDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("place_name")
    private String title;
    @JsonProperty("category_name")
    private String category;
    @JsonProperty("category_group_code")
    private String categoryGroupCode;
    @JsonProperty("category_group_name")
    private String description;
    @JsonProperty("phone")
    private String telephone;
    @JsonProperty("address_name")
    private String address;
    @JsonProperty("road_address_name")
    private String roadAddress;
    @JsonProperty("place_url")
    private String link;
    @JsonProperty("distance")
    private double distance;
    @JsonProperty("y")
    private double mapy;
    @JsonProperty("x")
    private double mapx;



}
