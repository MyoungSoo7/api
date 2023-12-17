package com.lms.api.food.controller;


import com.lms.api.food.dto.SearchLocalItem;
import com.lms.api.food.dto.SearchLocalReq;
import com.lms.api.food.dto.SearchLocalRes;
import com.lms.api.food.dto.WishListDto;
import com.lms.api.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final FoodService foodService;

    @GetMapping(path = "/nsearch")
    public ModelAndView NaverBlogSerch(@RequestParam(value = "query") String query) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList", foodService.NaverBlogSerch(query));

        return modelAndView;

    }

    @GetMapping(path = "/localsearch")
    public ModelAndView localSearchList(Model model, @RequestParam(value = "query") String query) throws Exception {

        List<SearchLocalItem>searchLocalItems=   foodService.localSearch(query).getItems();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output2");
        modelAndView.addObject("outputFormList",searchLocalItems);
        return modelAndView;
    }

    @GetMapping("/wish")
    public WishListDto search(@RequestParam String query) {
        return foodService.wishSearch(query);
    }


}
