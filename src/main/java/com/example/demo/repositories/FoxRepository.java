package com.example.demo.repositories;

import com.example.demo.models.Fox;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoxRepository extends CrudRepository<Fox, Long> {

  Fox findByName(String name);

  Fox findFoxById(Long id);

}
