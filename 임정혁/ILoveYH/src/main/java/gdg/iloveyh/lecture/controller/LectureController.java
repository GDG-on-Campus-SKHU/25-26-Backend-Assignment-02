package gdg.iloveyh.lecture.controller;

import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import gdg.iloveyh.lecture.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureResponse> create(@Valid @RequestBody LectureRequest request) {
        log.info("POST /lecture - 강의 생성 요청");
        LectureResponse response = lectureService.create(request);
        log.info("POST /lecture - 강의 생성 완료: id={}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LectureResponse>> getAll() {
        log.info("GET /lecture - 전체 강의 조회 요청");
        List<LectureResponse> responses = lectureService.getAll();
        log.info("GET /lecture - 전체 강의 조회 완료: {}개", responses.size());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureResponse> getById(@PathVariable Long id) {
        log.info("GET /lecture/{} - 강의 조회 요청", id);
        LectureResponse response = lectureService.getById(id);
        log.info("GET /lecture/{} - 강의 조회 완료", id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LectureResponse> update(@PathVariable Long id, @Valid @RequestBody LectureRequest request) {
        log.info("PATCH /lecture/{} - 강의 수정 요청", id);
        LectureResponse response = lectureService.update(id, request);
        log.info("PATCH /lecture/{} - 강의 수정 완료", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /lecture/{} - 강의 삭제 요청", id);
        lectureService.delete(id);
        log.info("DELETE /lecture/{} - 강의 삭제 완료", id);
        return ResponseEntity.noContent().build();
    }
}
