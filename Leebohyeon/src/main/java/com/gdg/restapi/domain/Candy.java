package com.gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Candy {
    private Long id;
    private String name;
    private int price;

    public static Candy of(String name, int price) {
        return new Candy(null, name, price);
    }
}
