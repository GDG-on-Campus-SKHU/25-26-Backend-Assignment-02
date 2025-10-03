package com.gdg.restapi.repository;

import com.gdg.restapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final HashMap<Long, User> store = new HashMap<>();
    private Long id = 0L;

    @Override
    public User save(User user){
        user.setUserId(++id);
        store.put(user.getUserId(), user);
        return user;
    }
    @Override
    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<User> findByUserId(Long userId){
        return Optional.ofNullable(store.get(userId));
    }
    @Override
    public List<User> findByPhone(String phone){
        return new ArrayList<>(store.values().stream()
                .filter(user -> user.getPhone().equals(phone))
                .toList()
        );
    }
    @Override
    public List<User> findByName(String name){
        return new ArrayList<>(store.values().stream()
                .filter(user -> user.getName().equals(name))
                .toList()
        );
    }
    @Override
    public List<User> findByEmail(String email){
        return new ArrayList<>(store.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .toList()
        );
    }
    @Override
    public User update(Long userId, User user){
        store.put(userId,user);
        return user;
    }
    @Override
    public boolean delete(Long userId){
        return store.remove(userId) != null;
    }
}
