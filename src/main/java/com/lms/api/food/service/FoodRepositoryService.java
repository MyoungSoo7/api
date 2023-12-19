package com.lms.api.food.service;


import com.lms.api.food.dto.FoodCntDto;
import com.lms.api.food.entity.Food;
import com.lms.api.food.repository.FoodReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodRepositoryService {

    private final FoodReposiroty foodReposiroty;

    public Food save(Food food) {
        return foodReposiroty.save(food);
    }

    public List<FoodCntDto> findFoodCnt() {
        return  foodReposiroty.findFoodCnt();
    }


}
