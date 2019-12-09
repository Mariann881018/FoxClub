package com.example.demo.services;

import com.example.demo.models.Food;
import com.example.demo.repositories.FoodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

  private FoodRepository foodRepository;

  @Autowired
  public FoodService(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  public List<Food> findAllFood() {
    List<Food> foodList = new ArrayList<>();
    foodRepository.findAll().forEach(foodList::add);
    return foodList;
  }

  public Food getRandom() {
    Random rand = new Random();
    Food randomFood = findAllFood().get(rand.nextInt(findAllFood().size()));
    return randomFood;
  }

  public Food findByFoodName(String name) {
     return foodRepository.findByFoodName(name);
  }
}
