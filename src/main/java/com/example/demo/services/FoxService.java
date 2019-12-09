package com.example.demo.services;

import com.example.demo.models.Fox;
import com.example.demo.models.Trick;
import com.example.demo.models.User;
import com.example.demo.repositories.FoxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoxService {
  private FoxRepository foxRepository;
  private FoodService foodService;
  private DrinkService drinkService;
  private TrickService trickService;

  @Autowired
  public FoxService(FoxRepository foxRepository,
                    FoodService foodService, DrinkService drinkService,
                    TrickService trickService) {
    this.foxRepository = foxRepository;
    this.foodService = foodService;
    this.drinkService = drinkService;
    this.trickService = trickService;
  }

  public List<Fox> findAllFox() {
    List<Fox> foxes = new ArrayList<>();
    foxRepository.findAll().forEach(foxes::add);
    return foxes;
  }

  public Fox findById(Long foxId) {
    return foxRepository.findFoxById(foxId);
  }

  public Fox findByName(String foxName) {
    return foxRepository.findByName(foxName);
  }

  public void saveFox(Fox fox) {
    foxRepository.save(fox);
  }

  public void deleteFox(Fox fox) {
    foxRepository.delete(fox);
  }

  public void changeNutri(User user, String food, String drink) {
    Fox fox = user.getFox();
    fox.setFood(foodService.findByFoodName(food));
    fox.setDrink(drinkService.findDrinkByDrinkName(drink));
    saveFox(fox);
  }

  public void learnTrick(User user, String trick) {
    Fox fox = user.getFox();
    Trick newTrick = trickService.findTrickByTrickName(trick);
    if (!fox.getTrickList().contains(newTrick)) {
      fox.getTrickList().add(newTrick);
      newTrick.getFoxes().add(fox);
      trickService.saveTrick(newTrick);
      saveFox(fox);
    }
  }
}
