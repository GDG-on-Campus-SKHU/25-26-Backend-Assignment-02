package com.example.restapi.app.dto;

import lombok.Getter;

@Getter
public class CartRequestDto {
    private String item;
    private int quantity;
}
