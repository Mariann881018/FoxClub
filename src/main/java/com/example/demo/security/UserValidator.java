package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Autowired
  private UserService userService;

  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    User user = (User) o;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
    if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
      errors.rejectValue("username", "Size.user.username");
    }
    if (userService.findUserByUsername(user.getUsername()) != null) {
      errors.rejectValue("username", "Duplicate.user.username");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "Size.user.password");
    }
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fox", "NotEmpty");
  }
}