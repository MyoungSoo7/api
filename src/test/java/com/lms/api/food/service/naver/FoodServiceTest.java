package com.lms.api.food.service.naver;

import com.lms.api.food.dto.naver.SearchLocalRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Slf4j
@SpringBootTest(
        properties = {
                "spring.config.location=" +
                        "classpath:application.yml"
        }
)
@Transactional
@RequiredArgsConstructor
public class FoodServiceTest {


    @Autowired
    private FoodService foodService;
    @Value(value = "${naver.client.id}")
    private String naverClientId;
    @Value(value = "${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;
    private static final String KAKAO_LOCAL_CATEGORY_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    @Autowired
    RestTemplate restTemplate;
    private static final String FOOD_CATEGORY = "FD6";
    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;
/*

    @Test
    @DisplayName("네이버 푸드서비스")
    void foodService(){


        var uri = UriComponentsBuilder
                .fromUriString(naverLocalSearchUrl)
                .queryParams(req.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};
        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        System.out.println(responseEntity.getBody().toString());
    }
*/

    @Test
    @DisplayName("카카오 푸드서비스")
    void kakaoFoodService(){


        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_CATEGORY_SEARCH_URL);
        uriBuilder.queryParam("query", "갈비탕");
        uriBuilder.queryParam("category_group_code",  FOOD_CATEGORY);
        uriBuilder.queryParam("size", 10);
        uriBuilder.queryParam("page", 1);
        uriBuilder.queryParam("sort", "accuracy");
        URI uri =uriBuilder.build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};
        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        System.out.println(responseEntity.getBody().toString());

        /*restTemplate.exchange(uri, HttpMethod.GET, httpEntity, SearchLocalRes.class).getBody().getDocumentList();*/
      /*  for(DocumentDto d : restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody().getDocumentList()){
            System.out.println(d.toString());
        }

        MetaDto m = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody().getMetaDto();
        System.out.println(m.toString());*/



    }

}