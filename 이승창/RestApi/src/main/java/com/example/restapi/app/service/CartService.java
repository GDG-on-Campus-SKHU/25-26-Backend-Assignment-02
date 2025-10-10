package com.example.restapi.app.service;

import com.example.restapi.app.dto.CartRequestDto;
import com.example.restapi.app.dto.CartResponseDto;
import com.example.restapi.app.mapper.CartMapper;
import com.example.restapi.app.repository.CartRepository;
import com.example.restapi.app.domain.Cart;
import com.example.restapi.global.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartResponseDto create(CartRequestDto cartRequestDto) {
        Cart cart = cartMapper.toEntity(cartRequestDto);
        Cart saved = cartRepository.save(cart);
        return cartMapper.toDto(saved);
    }

    public List<CartResponseDto> getAll(){
        return cartRepository.findAll().stream()
                .map(cartMapper::toDto)
                .toList();
    }

    public CartResponseDto getCartById(Long id){
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        return cartMapper.toDto(cart);
    }

    public CartResponseDto updateCart(Long id, CartRequestDto cartRequestDto){
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));

        cart.update(cartRequestDto.getItem(), cartRequestDto.getQuantity());
        return cartMapper.toDto(cart);
    }

    public void deleteCartById(Long id){
        if (cartRepository.findById(id).isEmpty()) {
            throw new CartNotFoundException(id);
        }
        cartRepository.deleteById(id);
    }
}
