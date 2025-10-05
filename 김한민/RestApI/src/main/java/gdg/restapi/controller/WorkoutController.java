package gdg.restapi.controller;

import java.util.List;

import gdg.restapi.dto.WorkoutPatchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdg.restapi.dto.WorkoutRequest;
import gdg.restapi.dto.WorkoutResponse;
import gdg.restapi.service.WorkoutService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService service;

    @PostMapping
    public ResponseEntity<WorkoutResponse> create(@RequestBody WorkoutRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> putUpdate(@PathVariable Long id,
                                                     @RequestBody WorkoutRequest request) {
        return service.putUpdate(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 과제 요구: PATCH 메서드 제공 (부분 수정) */
    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutResponse> patchUpdate(@PathVariable Long id,
                                                       @RequestBody WorkoutPatchRequest request) {
        return service.patchUpdate(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}