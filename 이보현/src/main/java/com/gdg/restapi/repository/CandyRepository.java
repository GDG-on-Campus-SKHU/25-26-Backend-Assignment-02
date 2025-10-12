package com.gdg.restapi.repository;

import com.gdg.restapi.domain.Candy;

import java.util.List;
import java.util.Optional;

public interface CandyRepository {
    Candy save(Candy candy);
    List<Candy> findAll();
    Optional<Candy> findById(Long id);
    Candy update(Long id,Candy candy);
    boolean delete(Long id);
}
