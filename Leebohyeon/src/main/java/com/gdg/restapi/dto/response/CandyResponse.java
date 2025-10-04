package com.gdg.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandyResponse {
    private long id;
    private String name;
    private int price;
}
