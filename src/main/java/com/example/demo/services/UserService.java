package com.example.demo.services;

import com.example.demo.models.Fox;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;
  private FoxService foxService;
  private FoodService foodService;
  private DrinkService drinkService;
  private BCryptPasswordEncoder passwordEncoder;
  private RoleService roleService;


  @Autowired
  public UserService(UserRepository userRepository, FoxService foxService,
                     FoodService foodService, DrinkService drinkService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
    this.userRepository = userRepository;
    this.foxService = foxService;
    this.foodService = foodService;
    this.drinkService = drinkService;
    this.passwordEncoder = passwordEncoder;
    this.roleService = roleService;
  }

  public List<User> findAllUser() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  public User findUserById(Long userId) {
    return userRepository.findUserById(userId);
  }

  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User save(User user) {
    return userRepository.save(user);
  }


  public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEmail(user.getEmail());
    Fox fox = new Fox(user.getFox().getName());
    fox.setFood(foodService.getRandom());
    fox.setDrink(drinkService.getRandom());
    foxService.saveFox(fox);
    fox.setUser(user);
    user.setFox(fox);
    saveUserAndUpdateRole(user);
  }

  public void saveUserAndUpdateRole(User user) {
    this.save(user);
    roleService.updateRole(user);
  }
}
