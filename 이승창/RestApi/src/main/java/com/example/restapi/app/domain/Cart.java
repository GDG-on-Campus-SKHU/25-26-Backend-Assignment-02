package com.example.restapi.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Cart {
    private Long id;
    private String item;
    private int quantity;

    public void update(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // 정적 팩토리 메서드 (새 장바구니 생성)
    public static Cart create(String item, int quantity) {
        return new Cart(null, item, quantity);
    }

    // Repository에서 id를 부여할 때만 사용
    public Cart withId(Long id) {
        return new Cart(id, this.item, this.quantity);
    }
}
