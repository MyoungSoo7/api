package com.lms.api.food.repository;


import com.lms.api.food.dto.naver.FoodCntDto;
import com.lms.api.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodReposiroty extends JpaRepository<Food, Long> {

    @Query(
            value= """
                SELECT f.id as id, f.food as food , count(f.food) as cnt                             
                FROM food f
                GROUP BY food
            """)
    List<FoodCntDto> findFoodCnt();

}
