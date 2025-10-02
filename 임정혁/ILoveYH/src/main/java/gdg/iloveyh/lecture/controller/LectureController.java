package gdg.iloveyh.lecture.controller;

import gdg.iloveyh.lecture.domain.Lecture;
import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import gdg.iloveyh.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureResponse> create(@RequestBody   LectureRequest request) {
        return ResponseEntity.ok(lectureService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LectureResponse>> getAll() {
        return ResponseEntity.ok(lectureService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureResponse> getById(@PathVariable Long id) {
        LectureResponse response = lectureService.getById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LectureResponse> update(@PathVariable Long id, @RequestBody LectureRequest request) {
        LectureResponse response = lectureService.update(id, request);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LectureResponse> delete(@PathVariable Long id) {
        return lectureService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
