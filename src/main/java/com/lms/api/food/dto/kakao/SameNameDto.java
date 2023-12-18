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
public class SameNameDto {

    @JsonProperty("region")
    private String[] region;
    @JsonProperty("keyword")
    private String keyword;
    @JsonProperty("selected_region")
    private String selectedRegion;

}
