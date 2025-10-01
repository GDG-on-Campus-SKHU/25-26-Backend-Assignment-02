package com.example.restapi.app.controller;
//
//import com.example.restapi.app.dto.CartRequestDto;
//import com.example.restapi.app.dto.CartResponseDto;
//import com.example.restapi.app.service.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("cart")
//public class CartController {
//    private final CartService cartService;
//
//    @PostMapping
//    public ResponseEntity<CartResponseDto> createCart(@RequestBody CartRequestDto cartRequestDto){
//        return ResponseEntity.ok(cartService.create(cartRequestDto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CartResponseDto>> getAllCarts(){
//        return ResponseEntity.ok(cartService.getAll());
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<CartResponseDto> getCartById(@PathVariable Long id){
//        return ResponseEntity.ok(cartService.getCartById(id));
//    }
//
//    @PatchMapping("{id}")
//    public ResponseEntity<CartResponseDto> updateCartById(@PathVariable Long id, @RequestBody CartRequestDto cartRequestDto){
//        return ResponseEntity.ok(cartService.updateCart(id, cartRequestDto));
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteCartById(@PathVariable Long id){
//         cartService.deleteCartById(id);
//         return ResponseEntity.noContent().build();
//    }
//}

import com.example.restapi.app.dto.CartRequestDto;
import com.example.restapi.app.dto.CartResponseDto;
import com.example.restapi.app.service.CartService;
import com.example.restapi.global.dto.ApiResponseTemplate;
import com.example.restapi.global.exception.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 생성
    @PostMapping
    public ResponseEntity<ApiResponseTemplate<CartResponseDto>> createCart(@RequestBody CartRequestDto cartRequestDto) {
        CartResponseDto created = cartService.create(cartRequestDto);
        return ApiResponseTemplate.success(SuccessCode.CART_CREATED, created);
    }

    // 전체 장바구니 조회
    @GetMapping
    public ResponseEntity<ApiResponseTemplate<List<CartResponseDto>>> getAllCarts() {
        List<CartResponseDto> carts = cartService.getAll();
        return ApiResponseTemplate.success(SuccessCode.CART_LIST_FOUND, carts);
    }

    // 특정 장바구니 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseTemplate<CartResponseDto>> getCartById(@PathVariable Long id) {
        CartResponseDto cart = cartService.getCartById(id);
        return ApiResponseTemplate.success(SuccessCode.CART_FOUND, cart);
    }

    // 장바구니 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseTemplate<CartResponseDto>> updateCartById(
            @PathVariable Long id,
            @RequestBody CartRequestDto cartRequestDto
    ) {
        CartResponseDto updated = cartService.updateCart(id, cartRequestDto);
        return ApiResponseTemplate.success(SuccessCode.CART_UPDATED, updated);
    }

    // 장바구니 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseTemplate<Void>> deleteCartById(@PathVariable Long id) {
        cartService.deleteCartById(id);
        return ApiResponseTemplate.success(SuccessCode.CART_DELETED, null);
    }
}