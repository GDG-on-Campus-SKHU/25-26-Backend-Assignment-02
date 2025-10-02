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
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LectureResponse> update(@PathVariable Long id, @RequestBody LectureRequest request) {
        LectureResponse response = lectureService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lectureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
