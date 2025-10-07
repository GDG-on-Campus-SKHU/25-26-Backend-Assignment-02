package gdg.restapi.controller;

import gdg.restapi.dto.GameRequest;
import gdg.restapi.dto.GameResponse;
import gdg.restapi.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor // final 필드 자동 생성자
public class GameController {
    private final GameService service;

    @PostMapping
    public ResponseEntity<GameResponse> create(@RequestBody GameRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getById(@PathVariable Long id) {
        GameResponse response = service.getById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponse> update(@PathVariable Long id, @RequestBody GameRequest request) {
        GameResponse response = service.update(id, request);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

//  유저 정보 중 id가 일치하지 않을 경우 예외
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
