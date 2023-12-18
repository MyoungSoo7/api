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
    private String placeName;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("category_group_code")
    private String categoryGroupCode;
    @JsonProperty("category_group_name")
    private String categoryGroupName="FD6";
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("road_address_name")
    private String roadAddressName;
    @JsonProperty("place_url")
    private String placeUrl;
    @JsonProperty("distance")
    private double distance;
    @JsonProperty("y")
    private double latitude;
    @JsonProperty("x")
    private double longitude;



}
