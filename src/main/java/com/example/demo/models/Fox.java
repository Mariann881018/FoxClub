package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fox")
public class Fox {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "foxName", nullable = false)
  private String name;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "drink_id")
  private Drink drink;

  @ManyToOne
  @JoinColumn(name = "food_id")
  private Food food;

  @ManyToMany
  @JoinTable(
      name = "foxes_tricks",
      joinColumns = @JoinColumn(
          name = "fox_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "trick_id", referencedColumnName = "id"))
  private List<Trick> listOfTricks = new ArrayList<>();

  public Fox() {
  }

  public Fox(String name) {
    this.name = name;
  }

  public Fox(String name, Drink drink, Food food) {
    this.name = name;
    this.drink = drink;
    this.food = food;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Trick> getListOfTricks() {
    return listOfTricks;
  }

  public void setListOfTricks(List<Trick> listOfTricks) {
    this.listOfTricks = listOfTricks;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int numberOfTricks() {
    if (listOfTricks != null) {
      return listOfTricks.size();
    } else
      return 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  public Drink getDrink() {
    return drink;
  }

  public void setDrink(Drink drink) {
    this.drink = drink;
  }

  public List<Trick> getTrickList() {
    return listOfTricks;
  }

  public void setTrickList(List<String> trickList) {
    this.listOfTricks = listOfTricks;
  }

  public void addTrick(Trick trick) {
    this.listOfTricks.add(trick);
  }
}
