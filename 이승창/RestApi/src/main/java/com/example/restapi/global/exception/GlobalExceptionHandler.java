package com.example.restapi.global.exception;

import com.example.restapi.global.dto.ApiResponseTemplate;
import com.example.restapi.global.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 전역 예외 처리기임을 선언하는 애노테이션, 프로젝트 내의 모든 @RestController에서 발생한 예외 감지 및 처리
// 컨트롤러에서 예외처리 부분을 분리함으로써 역할을 확실히 나눌 수 있는 장점이 있음.
public class GlobalExceptionHandler {

    /*
    * @ExceptionHandler : 특정 예외 타입을 잡아서 처리할 수 있는 애노테이션
    * */
    // 유효성 검증 실패 (예: @Valid 실패)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleValidationException(MethodArgumentNotValidException e) {
        log.warn("Validation error: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.VALIDATION_EXCEPTION, null);
    }

    // 잘못된 인자 (IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.VALIDATION_EXCEPTION, null);
    }

    // 특정 엔티티를 찾을 수 없음 (예: Cart 없음)
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleCartNotFoundException(CartNotFoundException e) {
        log.warn("Cart not found: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.NOT_FOUND_CART_EXCEPTION, null);
    }


    // 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleException(Exception e) {
        log.error("Unexpected error", e);
        return ApiResponseTemplate.error(ErrorCode.INTERNAL_SERVER_EXCEPTION, null);
    }
}
