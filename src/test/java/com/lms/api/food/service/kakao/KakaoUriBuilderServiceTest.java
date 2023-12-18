package com.lms.api.food.service.kakao;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
@AllArgsConstructor
class KakaoUriBuilderServiceTest {

    @Autowired
    private KakaoUriBuilderService kakaoUriBuilderService;

    @Test
    @DisplayName("카카오 주소 검색 URI 생성 테스트")
    void buildUriByAddressSearch() {
        var result =  kakaoUriBuilderService.buildUriByAddressSearch("서울특별시 강남구 역삼동 825-25");
        System.out.println(result);

    }


}