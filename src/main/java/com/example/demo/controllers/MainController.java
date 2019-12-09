package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.security.UserValidator;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private UserService userService;
  private UserValidator userValidator;

  @Autowired
  public MainController(UserService userService, UserValidator userValidator) {
    this.userService = userService;
    this.userValidator = userValidator;
  }

  @GetMapping("/")
  public String home() {
    return "welcome";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(@ModelAttribute("user") User user, BindingResult result) {
    userValidator.validate(user, result);
    if (result.hasErrors()) {
      return "register";
    }
    userService.registerUser(user);
    return "redirect:/index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String sendLogin(Model model) {
    model.addAttribute("user", getLoggedUser());
    return "redirect:/index";
  }

  @GetMapping("/index")
  public String index(Model model) {
    model.addAttribute("user", getLoggedUser());
    return "index";
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
