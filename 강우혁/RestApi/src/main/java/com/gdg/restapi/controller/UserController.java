package com.gdg.restapi.controller;

import com.gdg.restapi.domain.User;
import com.gdg.restapi.dto.StudentRequest;
import com.gdg.restapi.dto.StudentResponse;
import com.gdg.restapi.dto.UserRequest;
import com.gdg.restapi.dto.UserResponse;
import com.gdg.restapi.repository.UserRepository;
import com.gdg.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService s;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(s.create(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(s.getAll());
    }
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponse> getByUserId(@PathVariable Long userId){
        UserResponse response = s.getByUserId(userId);
        return (response != null) ? ResponseEntity.ok(response):ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserResponse>> getByName(@PathVariable String name){
        return ResponseEntity.ok(s.getByName(name));
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<UserResponse>> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(s.getByPhone(phone));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<UserResponse>> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(s.getByEmail(email));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone) {

        if (userId != null) {
            return ResponseEntity.ok(s.getByUserId(userId));
        } else if (name != null) {
            return ResponseEntity.ok(s.getByName(name));
        } else if (phone != null) {
            return ResponseEntity.ok(s.getByPhone(phone));
        }
        return ResponseEntity.badRequest().body("검색 조건을 이용해주세요~");
    }

    @PutMapping("/id/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        UserResponse response = s.update(userId, userRequest);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/id/{userId}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long userId){
        return s.delete(userId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
