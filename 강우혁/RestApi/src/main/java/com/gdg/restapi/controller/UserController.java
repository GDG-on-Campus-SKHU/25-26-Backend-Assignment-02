package com.gdg.restapi.controller;

import com.gdg.restapi.dto.UserRequest;
import com.gdg.restapi.dto.UserResponse;
import com.gdg.restapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid UserRequest userRequest){
        return ResponseEntity.ok(service.create(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponse> getByUserId(@PathVariable Long userId){
        UserResponse response = service.getByUserId(userId);
        return (response != null) ? ResponseEntity.ok(response):ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserResponse>> getByName(@PathVariable String name){
        return ResponseEntity.ok(service.getByName(name));
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<UserResponse>> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(service.getByPhone(phone));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<UserResponse>> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = true, defaultValue = "0") Long userId,
            @RequestParam(required = true, defaultValue = "guest") String name,
            @RequestParam(required = true, defaultValue = "010-1234-5678") String phone,
            @RequestParam(required = true, defaultValue = "guest@gmail.com") String email
    )
    {

        if (userId != null) {
            return ResponseEntity.ok(service.getByUserId(userId));
        } else if (name != null) {
            return ResponseEntity.ok(service.getByName(name));
        } else if (phone != null) {
            return ResponseEntity.ok(service.getByPhone(phone));
        } else if (email != null){
            return ResponseEntity.ok(service.getByEmail(email));
        }
        return ResponseEntity.badRequest().body("검색 조건을 이용해주세요~");
    }

    @PutMapping("/id/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        UserResponse response = service.update(userId, userRequest);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/id/{userId}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long userId){
        return service.delete(userId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
