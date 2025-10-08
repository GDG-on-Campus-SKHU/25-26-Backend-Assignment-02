package com.gdg.restapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Student {
    private Long id;
    private String name;
    private String major;
}
