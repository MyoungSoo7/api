package com.lms.api.shopping.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.api.shopping.dto.NaverShoppingSearchResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;



class NaverShoppingServiceTest {

    //private final NaverShoppingService naverShoppingService;

    //@BeforeAll  모든 테스트 메서드 실행전에 딱한번
    //@AfterALL  모든테스트 메서드 실행후에 딱한 번
    //@BeforeEach  각각의 테스트 메서드 실행전에
    //@AfterEach 각각의 테스트 메서드 실행후에
    //@Disabled 해당 테스트 메서드를 실행하지 않음
    //@RepeatedTest(10) 해당 테스트 메서드를 10번 반복 실행
    //@parameterizedTest  테스트 메서드를 반복 실행하면서 다른 값을 넣어줄 수 있음
    //@Nested 테스트 클래스 안에 테스트 클래스를 만들어서 테스트를 그룹화 할 수 있음

    //Assertions  결과
    // assertAll 모든 테스트를 실행하고 결과를 한번에 확인할 수 있음
    // assertArrayEquals 배열이 같은지 확인
    // assertDoesNotThrow 예외가 발생하지 않는지 확인
    // assertEquals 값이 같은지 확인
    // assertIterableEquals Iterable 객체가 같은지 확인
    // assertNotEquals 값이 다른지 확인
    // assertNotSame 객체가 다른지 확인
    // assertNull 객체가 null인지 확인
    // assertSame 객체가 같은지 확인
    // assertThat 조건에 맞는지 확인
    // assertTrue 조건이 참인지 확인
    // assertFalse 조건이 거짓인지 확인
    // assertThrows 예외가 발생하는지 확인
    // assertTimeout 시간안에 실행이 완료되는지 확인
    // assertTimeoutPreemptively 시간안에 실행이 완료되는지 확인하고 완료되지 않으면 중단

    //Assumption 전제문 가정
    //assumeFalse 조건이 거짓이면 테스트를 중단
    //assumeTrue 조건이 참이면 테스트를 중단
    //assumingThat 조건이 참이면 테스트를 실행하고 거짓이면 테스트를 중단

    //@SpringBootTest : 모든 Bean을 로드하여 테스트
    //@ExtendWith(SpringExtension.class) : JUnit5에서 Spring을 사용할 수 있도록 지원
    //MockBean : 테스트용 Bean을 등록
    //@WebMvcTest : {Class명.class}에 작성된 클래스만 실제로 로드하여 테스트 (컨트롤러와 연관된 Bean이 모두 로드됨)
    //@AutoConfigureMockMvc : MockMvc를 자동으로 설정해줌
    //


    @ParameterizedTest
    @CsvSource({"아이패드, 1", "아이폰, 2", "아이맥, 3"})
    @DisplayName("네이버 쇼핑 API 테스트")
    void test(final String input) throws Exception{
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "zdqMoIkFaK8uKvC2oNY2");
        headers.add("X-Naver-Client-Secret", "");
        String body ="";
        HttpEntity<String> requestEntity = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();

        //when
        String response = restTemplate.exchange("https://openapi.naver.com/v1/search/shop.json?query="
                + input, HttpMethod.GET, requestEntity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode itemsNode = objectMapper.readTree(response).get("items");
        //then
        List<NaverShoppingSearchResultDto> list = objectMapper
                .readerFor(new TypeReference<List<NaverShoppingSearchResultDto>>() {
                })
                .readValue(itemsNode);

        //then
        System.out.println(list);

    }

}