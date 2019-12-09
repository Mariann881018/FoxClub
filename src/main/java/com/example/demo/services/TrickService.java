package com.example.demo.services;

import com.example.demo.models.Fox;
import com.example.demo.models.Trick;
import com.example.demo.repositories.TrickRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrickService {

  private TrickRepository trickRepository;

  @Autowired
  public TrickService(TrickRepository trickRepository) {
    this.trickRepository = trickRepository;
  }

  public List<Trick> findAllTrick() {
    List<Trick> tricks = new ArrayList<>();
    trickRepository.findAll().forEach(tricks::add);
    return tricks;
  }

  public List<Trick> findAllTricksNotKnownByFox(Fox fox) {
    List<Trick> foxTricks = fox.getTrickList();
    List<Trick> allTricks = findAllTrick();
    return allTricks.stream()
        .filter(t -> !foxTricks.contains(t))
        .collect(Collectors.toList());
  }

  public Trick findTrickById(Long trickId) {
    return trickRepository.findTrickById(trickId);
  }

  public Trick findTrickByTrickName(String userName) {
    return trickRepository.findByTrickName(userName);
  }

  public void saveTrick(Trick trick) {
    trickRepository.save(trick);
  }

}
