package com.gdg.restapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long userId;
    private String name;
    private String phone;
    private String email;
}
