package com.gdg.restapi.repository;
import java.util.*;
import com.gdg.restapi.domain.User;
public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findByUserId(Long userId);
    List<User> findByName(String name);
    List<User> findByPhone(String phone);
    List<User> findByEmail(String email);
    User update(Long userId, User user);
    boolean delete(Long userId);
}
