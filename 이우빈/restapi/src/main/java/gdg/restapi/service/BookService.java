package gdg.restapi.service;

import gdg.restapi.domain.Book;
import gdg.restapi.dto.request.BookRequest;
import gdg.restapi.dto.response.BookResponse;
import gdg.restapi.exception.IdNotFoundException;
import gdg.restapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse create(BookRequest request) {
        Book book = new Book(null, request.getTitle(), request.getAuthor()); // 선언 및 초기화
        Book saved =  bookRepository.save(book); // saved에 insert, save의 ++seq로 인해 id 1 증가
        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor());
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor()))
                .toList();
    }

    public BookResponse getById(Long id) {
        return bookRepository.findById(id)
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor()))
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    public BookResponse update(Long id, BookRequest request) {
        bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        Book updated = bookRepository.update(id, new Book(id, request.getTitle(), request.getAuthor()));
        return new BookResponse(updated.getId(), updated.getTitle(), updated.getAuthor());
    }

    public ResponseEntity<Void> delete(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        bookRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
