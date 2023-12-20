package com.lms.api.food.controller;


import com.lms.api.food.repository.FoodCntDto;
import com.lms.api.food.dto.FoodInputDto;
import com.lms.api.food.dto.PageHandler;
import com.lms.api.food.dto.SearchLocalRes;
import com.lms.api.food.service.FoodRepositoryService;
import com.lms.api.food.service.kakao.CategorySearchService;
import com.lms.api.food.service.naver.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final FoodService foodService;
    private final CategorySearchService categorySearchService;
    private final FoodRepositoryService foodRepositoryService;

    @RequestMapping("/")
    public String index() {
        return "main";
    }

    //** redis 낙관적 락 , 비관적락 lock 사용
    //*** redis db 커넥션 과부하 해결
    @PostMapping("/search")
    public ModelAndView searchFoodApi(@ModelAttribute FoodInputDto food) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("food-search");
        SearchLocalRes searchLocalRes = foodService.localSearch(food.getFood(), food.getSort());
        if(searchLocalRes==null){
            PageHandler pageHandler = new PageHandler( Integer.parseInt(food.getPage()) ,10 );
            modelAndView.addObject("foodList",categorySearchService.requestFoodCategorySearch(food.getFood(), Integer.parseInt(food.getPage())).getDocumentList());
            modelAndView.addObject("pageHandler", pageHandler);
            modelAndView.addObject("keyword", food.getFood());
            modelAndView.addObject("foodListWithCnt", foodRepositoryService.findFoodCnt());
            return modelAndView;
        }
        modelAndView.addObject("foodList",searchLocalRes.getItems());
        modelAndView.addObject("foodListWithCnt", foodRepositoryService.findFoodCnt());

        return modelAndView;
    }

    @GetMapping("/foodCnt")
    public List<FoodCntDto> foodListWithCount() {
        return foodRepositoryService.findFoodCnt();
    }

}
