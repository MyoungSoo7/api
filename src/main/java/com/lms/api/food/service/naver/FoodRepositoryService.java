package com.lms.api.food.service.naver;


import com.lms.api.food.dto.naver.FoodCntDto;
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
    //private final LocalItemRepository localItemRepository;

    public Food save(Food food) {
        return foodReposiroty.save(food);
    }


 /*   public Page<SearchLocalItem> findAllLocalItem(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return localItemRepository.findAll(pageable);
    }*/

    public List<FoodCntDto> findFoodCnt() {
        return  foodReposiroty.findFoodCnt();
    }

    public void delete(Long id) {
        foodReposiroty.deleteById(id);
    }


}
