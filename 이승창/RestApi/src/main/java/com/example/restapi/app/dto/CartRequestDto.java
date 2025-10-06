package com.example.restapi.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartRequestDto {
    @NotBlank(message = "상품명은 필수입니다")
    private String item;

    @NotNull(message = "수량은 필수입니다.")
    private int quantity;
}
