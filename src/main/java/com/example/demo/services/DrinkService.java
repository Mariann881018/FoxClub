package com.example.demo.services;

import com.example.demo.models.Drink;
import com.example.demo.repositories.DrinkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

  private DrinkRepository drinkRepository;

  @Autowired
  public DrinkService(DrinkRepository drinkRepository) {
    this.drinkRepository = drinkRepository;
  }

  public List<Drink> findAllDrink() {
    List<Drink> drinkList = new ArrayList<>();
    drinkRepository.findAll().forEach(drinkList::add);
    return drinkList;
  }

  public Drink getRandom() {
    Random rand = new Random();
    Drink randomDrink = findAllDrink().get(rand.nextInt(findAllDrink().size()));
    return randomDrink;
  }

  public Drink findDrinkByDrinkName(String name) {
    return drinkRepository.findDrinkByDrinkName(name);
  }

}