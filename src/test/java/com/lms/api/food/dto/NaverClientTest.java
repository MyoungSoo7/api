package com.lms.api.food.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void localSearchTest() {
        var search = new SearchLocalReq();
        search.setQuery("갈비집");
        var result = naverClient.localSearch(search);
        System.out.println(result);
    }

}