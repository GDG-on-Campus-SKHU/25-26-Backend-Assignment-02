package gdg.restapi2.controller;

import gdg.restapi2.dto.StationRequest;
import gdg.restapi2.dto.StationResponse;
import gdg.restapi2.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MetroGameRecorder")
@RequiredArgsConstructor // final 필드 자동 생성자
public class MetroGameController {

    private final StationService service;

    @PostMapping
    public ResponseEntity<StationResponse> create(@RequestBody StationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<StationResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> getById(@PathVariable Long id) {
        StationResponse response = service.getById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StationResponse> update(@PathVariable Long id, @RequestBody StationRequest request) {
        StationResponse response = service.update(id, request);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}