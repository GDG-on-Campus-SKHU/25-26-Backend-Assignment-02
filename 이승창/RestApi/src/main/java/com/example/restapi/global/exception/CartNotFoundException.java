package com.example.restapi.global.exception;

// CustomException
public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Long id) {
        super("Cart with id " + id + " not found.");
    }
}
