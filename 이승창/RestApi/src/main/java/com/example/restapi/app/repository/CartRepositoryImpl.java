package com.example.restapi.app.repository;

import com.example.restapi.app.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository // 인터페이스가 아닌 구현체에 @Repository를 붙이는 이유 -> 인터페이스는 new 연산이 불가능하여, 스프링이 빈 등록을 할 수 없음.
public class CartRepositoryImpl implements CartRepository {
    private final Map<Long, Cart> storage = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart = cart.withId(++sequence); // 새 객체 생성
        }
        storage.put(cart.getId(), cart);
        return cart;
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
