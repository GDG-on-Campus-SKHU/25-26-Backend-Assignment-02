package com.gdg.restapi.service;

import com.gdg.restapi.domain.User;
import com.gdg.restapi.dto.UserRequest;
import com.gdg.restapi.dto.UserResponse;
import com.gdg.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse create(UserRequest request){
        User user = User.builder().
                name(request.name()).
                phone(request.phone()).
                email(request.email()).
                build();

        User store = repository.save(user);
        return new UserResponse(
                store.getUserId(),
                store.getName(),
                store.getPhone(),
                store.getEmail());
    }

    public List<UserResponse> getAll(){
        return repository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail())).
                collect(Collectors.toList());
    }
    public UserResponse getByUserId(Long userId){
        return repository.findByUserId(userId)
                .map(user-> new UserResponse(
                                user.getUserId(),
                                user.getName(),
                                user.getPhone(),
                                user.getEmail())).
                orElse(null);
    }
    public List<UserResponse> getByName(String name){
        return repository.findByName(name).stream()
                .map(user-> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail()))
                .collect(Collectors.toList());
    }
    public List<UserResponse> getByPhone(String phone){
        return repository.findByPhone(phone).stream()
                .map(user-> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail()))
                .collect(Collectors.toList());
    }
    public List<UserResponse> getByEmail(String Email){
        return repository.findByEmail(Email).stream()
                .map(user-> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail()))
                .collect(Collectors.toList());
    }
    public UserResponse update(Long userId, UserRequest request){
        return repository.findByUserId(userId).map(user->{
            user.setName(request.name());
            user.setPhone(request.phone());
            user.setEmail(request.email());
            User update = repository.update(userId, user);
            return new UserResponse(
                    update.getUserId(),
                    update.getName(),
                    update.getPhone(),
                    update.getEmail());
        }).orElse(null);
    }
    public boolean delete(Long userId){
        return repository.delete(userId);
    }

}
