package gdg.restapi.controller;

import gdg.restapi.dto.request.BookRequest;
import gdg.restapi.dto.response.BookResponse;
import gdg.restapi.service.BookService;
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

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request) {
        //200 OK 대신 201 created + location 헤더가 REST 관례에 맞음
        BookResponse response = bookService.create(request);
        URI location = URI.create("/books/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        // 200 OK, 모든 요소 출력
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        BookResponse response = bookService.getById(id);
        // response != null일 경우 200 OK + response, null일 경우 404 not found
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody BookRequest request) {
        // NPE 방지를 위한 null 반환 대신 Optional 사용
        return bookService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // delete 성공했을 경우 noContent(): 응답 바디 x, delete 실패했을 경우 404 not found
        return bookService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
