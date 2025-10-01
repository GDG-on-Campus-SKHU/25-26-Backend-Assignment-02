package com.example.restapi.app.repository;

import com.example.restapi.app.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Cart save(Cart cart);
    List<Cart> findAll();
    Optional<Cart> findById(Long id);
    void deleteById(Long id);
}
