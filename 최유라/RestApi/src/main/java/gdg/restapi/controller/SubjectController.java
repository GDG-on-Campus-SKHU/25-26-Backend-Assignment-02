package gdg.restapi.controller;

import gdg.restapi.dto.SubjectRequest;
import gdg.restapi.dto.SubjectResponse;
import gdg.restapi.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor // final 필드 자동 생성자
public class SubjectController {

    private final SubjectService service;

    @PostMapping //학생 정보 입력, 한 번에 한 명씩만 입력해야 함
    public ResponseEntity<SubjectResponse> create(@RequestBody SubjectRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping //입력되어 있는 모든 학생 정보 출력(표시?)
    public ResponseEntity<List<SubjectResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> getById(@PathVariable Long id) {
        SubjectResponse response = service.getById(id);
        return (response == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}") //학생 정보 일부분만 수정가능,JSON타입으로 설정해줘야 작동함,학생 id 적어줘야 함
    public ResponseEntity<SubjectResponse> update(@PathVariable Long id, @RequestBody SubjectRequest request) {
        SubjectResponse response = service.update(id, request);
        return (response == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}") //학생 id 적어줘야, 본문엔 id 번호만 적기(예: 1,2)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}