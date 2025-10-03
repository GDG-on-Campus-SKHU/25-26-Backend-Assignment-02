package com.gdg.restapi.service;

import com.gdg.restapi.domain.User;
import com.gdg.restapi.dto.StudentResponse;
import com.gdg.restapi.dto.UserRequest;
import com.gdg.restapi.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import com.gdg.restapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository r;

    public UserResponse create(UserRequest request){
        User u = new User(null, request.getName(), request.getPhone(), request.getEmail());
        User s = r.save(u);
        return new UserResponse(s.getUserId(),s.getName(),s.getPhone(), s.getEmail());
    }

    public List<UserResponse> getAll(){
        return r.findAll().stream().
                map(u -> new UserResponse(u.getUserId(),u.getName(),u.getPhone(),u.getEmail())).
                collect(Collectors.toList());
    }
    public UserResponse getByUserId(Long userId){
        return r.findByUserId(userId).map(u-> new UserResponse(u.getUserId(),u.getName(),u.getPhone(),u.getEmail()))
                .orElse(null);
    }
    public List<UserResponse> getByName(String name){
        return r.findByName(name).stream()
                .map(u-> new UserResponse(u.getUserId(),u.getName(),u.getPhone(),u.getEmail()))
                .collect(Collectors.toList());
    }
    public List<UserResponse> getByPhone(String phone){
        return r.findByPhone(phone).stream()
                .map(u-> new UserResponse(u.getUserId(),u.getName(),u.getPhone(),u.getEmail()))
                .collect(Collectors.toList());
    }
    public List<UserResponse> getByEmail(String Email){
        return r.findByEmail(Email).stream()
                .map(u-> new UserResponse(u.getUserId(),u.getName(),u.getPhone(),u.getEmail()))
                .collect(Collectors.toList());
    }
    public UserResponse update(Long userId, UserRequest request){
        return r.findByUserId(userId).map(u->{
            u.setName(request.getName());
            u.setPhone(request.getPhone());
            u.setEmail(request.getEmail());
            User s = r.update(userId, u);
            return new UserResponse(s.getUserId(),s.getName(),s.getPhone(),s.getEmail());
        }).orElse(null);
    }
    public boolean delete(Long userId){
        return r.delete(userId);
    }

}
