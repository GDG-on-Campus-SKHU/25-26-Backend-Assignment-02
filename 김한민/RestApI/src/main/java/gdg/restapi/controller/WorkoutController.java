package gdg.restapi.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdg.restapi.dto.WorkoutPatchRequest;
import gdg.restapi.dto.WorkoutRequest;
import gdg.restapi.dto.WorkoutResponse;
import gdg.restapi.service.WorkoutService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService service;

    /** 등록 (POST) */
    @PostMapping
    public ResponseEntity<WorkoutResponse> create(@Valid @RequestBody WorkoutRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    /** 전체 조회 (GET) */
    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /** 단일 조회 (GET /{id}) */
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 전체 수정 (PUT) */
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> putUpdate(@PathVariable Long id,
                                                     @Valid @RequestBody WorkoutRequest request) {
        return service.putUpdate(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 부분 수정 (PATCH) */
    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutResponse> patchUpdate(@PathVariable Long id,
                                                       @RequestBody WorkoutPatchRequest request) {
        return service.patchUpdate(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 삭제 (DELETE) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /** 검증 예외 (400 Bad Request) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
