package com.lms.api.food.dto.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.api.food.dto.kakao.DocumentDto;
import com.lms.api.food.dto.kakao.MetaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {

    // 지역 검색 출력 결과를 변수화

    private String lastBuildDate; // 검색 결과를 생성한 시간이다.
    private int total; //  검색 결과 문서의 총 개수를 의미한다.
    private int start; // 검색 결과 문서 중, 문서의 시작점을 의미한다.
    private int display; // 검색된 검색 결과의 개수이다.
    private List<SearchLocalItem> items;
    //개별 검색 결과이며 title, link, description, address, mapx, mapy를 포함한다.

    @JsonProperty("meta")
    private MetaDto metaDto;
    @JsonProperty("documents")
    private List<DocumentDto> documentList;
}
