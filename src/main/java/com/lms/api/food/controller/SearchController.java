package com.lms.api.food.controller;


import com.lms.api.food.dto.kakao.DocumentDto;
import com.lms.api.food.dto.FoodCntDto;
import com.lms.api.food.dto.FoodInputDto;
import com.lms.api.food.dto.naver.SearchLocalItem;
import com.lms.api.food.entity.Food;
import com.lms.api.food.service.kakao.CategorySearchService;
import com.lms.api.food.service.FoodRepositoryService;
import com.lms.api.food.service.naver.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final FoodService foodService;
    private final FoodRepositoryService foodRepositoryService;
    private final CategorySearchService categorySearchService;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;
    @Value("${naver.url.search.blog}")
    private String naverBlogSearchUrl;
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/search")
    public ModelAndView searchFood(@ModelAttribute FoodInputDto food) throws Exception{
        // 음식 키워드 지역 네이버 검색
        List<SearchLocalItem> foodNaverList = foodService.localSearch(food.getFood(),naverLocalSearchUrl, food.getSort()).getItems();
        // 음식 키워드 저장
        foodRepositoryService.save(Food.builder()
                .food(food.getFood())
                .build());
        // 음식 키워드와 카운트 가져오기
        List<FoodCntDto> foodListWithCnt = foodRepositoryService.findFoodCnt();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output2");
        modelAndView.addObject("foodListWithCnt", foodListWithCnt);
        if(foodNaverList.size() == 0){
            modelAndView.addObject("foodList",foodNaverList);
            modelAndView.addObject("totalCnt", 1);
            modelAndView.addObject("page", 1);
            modelAndView.addObject("next", false);
        }else{
            //음식 키워드 카카오 검색
            List<DocumentDto> foodKakaoList = categorySearchService.requestFoodCategorySearch(food.getFood()).getDocumentList();
            int totalCnt = categorySearchService.requestFoodCategorySearch(food.getFood()).getMetaDto().getTotalCount();
            Boolean next = categorySearchService.requestFoodCategorySearch(food.getFood()).getMetaDto().getIsEnd();
            int page = categorySearchService.requestFoodCategorySearch(food.getFood()).getMetaDto().getPageableCount();

            modelAndView.addObject("foodList", foodKakaoList);
            modelAndView.addObject("foodNaverList", foodNaverList);
            modelAndView.addObject("totalCnt", totalCnt);
            modelAndView.addObject("page", page);
            modelAndView.addObject("next", next);

        }
        return modelAndView;
    }


}
