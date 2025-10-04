package com.gdg.restapi.repository;

import com.gdg.restapi.domain.Candy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CandyRepositoryImpl implements CandyRepository {
    private final Map<Long, Candy> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Candy save(Candy candy) {
        candy.setId(sequence++);
        store.put(candy.getId(), candy);
        return candy;
    }

    @Override
    public List<Candy> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Candy> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Candy update(Long id, Candy candy) {
        store.put(id, candy);
        return candy;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id)!=null;
    }
}
