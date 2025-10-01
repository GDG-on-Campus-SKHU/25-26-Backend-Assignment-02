package com.example.restapi.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cart {
    private Long id;
    private String item;
    private int quantity;

    public void update(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
