package com.example.demo.repositories;

import com.example.demo.models.Drink;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {

  List<Drink> findAll();

  Drink findDrinkByDrinkName(String name);

}
