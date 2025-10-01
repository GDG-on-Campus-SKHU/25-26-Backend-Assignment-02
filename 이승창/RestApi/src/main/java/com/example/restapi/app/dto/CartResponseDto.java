package com.example.restapi.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartResponseDto {
    private Long id;
    private String item;
    private int quantity;
}
