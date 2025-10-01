package com.example.restapi.app.mapper;

import com.example.restapi.app.dto.CartRequestDto;
import com.example.restapi.app.domain.Cart;
import com.example.restapi.app.dto.CartResponseDto;
import org.springframework.stereotype.Component;

@Component // 빈 등록, DI 가능, @Bean은 메소드에 붙임.
public class CartMapper {
    public Cart toEntity(CartRequestDto cartRequestDto){
        return Cart.builder()
                .id(null)
                .item(cartRequestDto.getItem())
                .quantity(cartRequestDto.getQuantity())
                .build();
    }

    public CartResponseDto toDto(Cart cart){
        return new CartResponseDto(cart.getId(), cart.getItem(), cart.getQuantity());
    }
}
