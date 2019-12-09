package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "trick")
public class Trick {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "trickName", nullable = false)
  private String trickName;

  @ManyToMany(mappedBy = "listOfTricks", fetch = FetchType.EAGER)
  private List<Fox> foxes;

  public Trick() {
  }

  public Trick(String trickName) {
    this.trickName = trickName;
  }

  public String getTrickName() {
    return trickName;
  }

  public void setTrickName(String trickName) {
    this.trickName = trickName;
  }

  public List<Fox> getFoxes() {
    return foxes;
  }

  public void setFoxes(List<Fox> foxes) {
    this.foxes = foxes;
  }
}
