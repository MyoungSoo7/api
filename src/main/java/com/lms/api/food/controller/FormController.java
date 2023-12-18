package com.lms.api.food.controller;


import com.lms.api.food.dto.naver.FoodCntDto;
import com.lms.api.food.dto.naver.FoodInputDto;
import com.lms.api.food.dto.naver.SearchLocalItem;
import com.lms.api.food.entity.Food;
import com.lms.api.food.service.naver.FoodRepositoryService;
import com.lms.api.food.service.naver.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class FormController {

    private final FoodService foodService;
    private final FoodRepositoryService foodRepositoryService;

    @RequestMapping("/")
    public String form() {
        return "main";
    }


    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute FoodInputDto food) throws Exception{
        List<SearchLocalItem> searchLocalItems=   foodService.localSearch(food.getFood()).getItems();

        foodRepositoryService.save(Food.builder()
                .food(food.getFood())
                .build());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output2");
        modelAndView.addObject("outputFormList",searchLocalItems);

        List<FoodCntDto> list = foodRepositoryService.findFoodCnt();
        modelAndView.addObject("foodList", list);


        return modelAndView;
    }

}
