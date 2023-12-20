package com.lms.api.food.controller;


import com.lms.api.food.dto.PageHandler;
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

import java.io.IOException;
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
 /*   @Value("${naver.url.search.blog}")
    private String naverBlogSearchUrl;*/
    @RequestMapping("/")
    public String index() {
        return "main.html";
    }

    @RequestMapping("/search")
    public ModelAndView searchFood(@ModelAttribute FoodInputDto food) throws IOException {
        // 음식 키워드 저장
        saveFoodKeyword(food);
        // 음식 키워드와 카운트 가져오기
        List<FoodCntDto> foodListWithCnt= foodListWithCount();

        int page =1;
        int pageSize = 10;

        //음식 키워드 카카오 검색
        List<DocumentDto> foodKakaoList = categorySearchService.requestFoodCategorySearch(food.getFood(),page).getDocumentList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output2.html");
        modelAndView.addObject("foodListWithCnt", foodListWithCnt);
        if(foodKakaoList.isEmpty()){
            // 음식 키워드 지역 네이버 검색
            List<SearchLocalItem> foodNaverList = foodService.localSearch(food.getFood(),naverLocalSearchUrl, food.getSort()).getItems();
            modelAndView.addObject("foodList",foodNaverList);
            modelAndView.addObject("totalCnt", foodNaverList.size());
            modelAndView.addObject("page", 1);
            modelAndView.addObject("next", false);
        }else{

            int totalCnt = categorySearchService.requestFoodCategorySearch(food.getFood(),page).getMetaDto().getTotalCount();
            Boolean next = categorySearchService.requestFoodCategorySearch(food.getFood(),page).getMetaDto().getIsEnd();
            int totalpage = categorySearchService.requestFoodCategorySearch(food.getFood(),page).getMetaDto().getPageableCount();
            System.out.println("totalCnt"+totalCnt);
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            System.out.println("pageHandler"+pageHandler.toString());
            modelAndView.addObject("keyword", food.getFood());
            modelAndView.addObject("foodList", foodKakaoList);
            modelAndView.addObject("totalCnt", totalCnt);
            modelAndView.addObject("totalpage", totalpage);
            modelAndView.addObject("offset", (page-1)*pageSize);
            modelAndView.addObject("page", page);
            modelAndView.addObject("next", next);
            modelAndView.addObject("pageHandler", pageHandler);

        }
        return modelAndView;
    }

    @RequestMapping("/searchPage")
    public ModelAndView searchFood(@RequestParam(value="keyword") String keyword
            , @RequestParam(value = "page")int page) throws IOException {

        // 음식 키워드와 카운트 가져오기
        List<FoodCntDto> foodListWithCnt = foodListWithCount();
        //음식 키워드 카카오 검색
        List<DocumentDto> foodKakaoList = categorySearchService.requestFoodCategorySearch(keyword, page).getDocumentList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output2.html");
        modelAndView.addObject("foodListWithCnt", foodListWithCnt);

        int pageSize = 10;
        int totalCnt = categorySearchService.requestFoodCategorySearch(keyword,page).getMetaDto().getTotalCount();
        Boolean next = categorySearchService.requestFoodCategorySearch(keyword,page).getMetaDto().getIsEnd();
        int totalpage = categorySearchService.requestFoodCategorySearch(keyword,page).getMetaDto().getPageableCount();
        System.out.println("totalCnt"+totalCnt);
        PageHandler pageHandler = new PageHandler(page,pageSize);

        System.out.println("pageHandler"+pageHandler.toString());
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("foodList", foodKakaoList);
        modelAndView.addObject("totalCnt", totalCnt);
        modelAndView.addObject("totalpage", totalpage);
        modelAndView.addObject("offset", (page-1)*pageSize);
        modelAndView.addObject("page", page);
        modelAndView.addObject("next", next);
        modelAndView.addObject("pageHandler", pageHandler);
        return modelAndView;
    }

    private List<FoodCntDto> foodListWithCount() {
        return foodRepositoryService.findFoodCnt();
    }

    private void saveFoodKeyword(FoodInputDto food) {
        foodRepositoryService.save(Food.builder()
                .food(food.getFood())
                .build());
    }


}
