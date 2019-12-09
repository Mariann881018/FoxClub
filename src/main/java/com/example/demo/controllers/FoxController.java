package com.example.demo.controllers;

import com.example.demo.models.Fox;
import com.example.demo.models.User;
import com.example.demo.services.DrinkService;
import com.example.demo.services.FoodService;
import com.example.demo.services.FoxService;
import com.example.demo.services.TrickService;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoxController {

  private FoxService foxService;
  private FoodService foodService;
  private DrinkService drinkService;
  private TrickService trickService;
  private UserService userService;

  @Autowired
  public FoxController(FoxService foxService, FoodService foodService,
                       DrinkService drinkService, TrickService trickService,
                       UserService userService) {
    this.foxService = foxService;
    this.foodService = foodService;
    this.drinkService = drinkService;
    this.trickService = trickService;
    this.userService = userService;
  }

  @GetMapping("/nutritionStore")
  public String getNutriStore(Model model) {
    model.addAttribute("user", getLoggedUser());
    model.addAttribute("foods", foodService.findAllFood());
    model.addAttribute("drinks", drinkService.findAllDrink());
    return "nutritionStore";
  }

  @PostMapping("/nutritionStore")
  public String changeFoodAndDrink(String food, String drink,
                                   Model model) {
    model.addAttribute("user", getLoggedUser());
    foxService.changeNutri(getLoggedUser(), food, drink);
    return "redirect:/index";
  }


  @GetMapping("/trickCenter")
  public String getTrickCenter(Model model) {
    model.addAttribute("user", getLoggedUser());
    Fox fox = getLoggedUser().getFox();
    model.addAttribute("tricks", trickService.findAllTricksNotKnownByFox(fox));
    return "trickCenter";
  }

  @PostMapping("/trickCenter")
  public String saveTrick(String trick) {
    foxService.learnTrick(getLoggedUser(), trick);
    return "redirect:/index";
  }

  private User getLoggedUser() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userService.findUserByUsername(userName);
  }
}
