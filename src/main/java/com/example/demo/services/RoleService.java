package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private RoleRepository roleRepository;
  private UserRepository userRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  public Role findRoleByName(String name) {
    return roleRepository.findRoleByName(name);
  }

  public List<Role> findAllRoles() {
    List<Role> roles = new ArrayList<>();
    roleRepository.findAll().forEach(role -> roles.add(role));
    return roles;
  }

  public List<Role> findAllRolesByUser(User user) {
    return this.findAllRoles().stream().filter(role -> role.getUsers()
        .stream().anyMatch(user1 -> !equals(user)))
        .collect(Collectors.toList());
  }

  public void updateRole(User user) {
    Role role = this.findRoleByName("USER");
    role.getUsers().add(user);
    user.getRoles().add(role);
    saveRole(role);
    userRepository.save(user);
  }

  public void saveRole(Role role) {
    roleRepository.save(role);
  }
}
