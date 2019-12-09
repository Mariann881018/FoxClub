package com.example.demo.repositories;

import com.example.demo.models.Trick;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrickRepository extends CrudRepository<Trick, Long> {

  Trick findTrickById(Long trickId);

  Trick findByTrickName(String userName);
}
