package com.example.demo.repositories;

import com.example.demo.models.Food;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

  List<Food> findAll();

  Food findByFoodName(String name);
}
