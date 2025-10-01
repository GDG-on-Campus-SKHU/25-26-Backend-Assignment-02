package com.example.restapi.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    // Cart 200 Ok
    CART_FOUND(HttpStatus.OK, "장바구니 조회 성공"),
    CART_UPDATED(HttpStatus.OK, "장바구니 수정 성공"),
    CART_LIST_FOUND(HttpStatus.OK, "전체 장바구니 조회 성공"),

    // 201 Created, Delete
    CART_CREATED(HttpStatus.CREATED, "장바구니 생성 성공"),
    CART_DELETED(HttpStatus.NO_CONTENT, "장바구니 삭제 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
