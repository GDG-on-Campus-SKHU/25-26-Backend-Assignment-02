package gdg.restapi.service;

import gdg.restapi.domain.Book;
import gdg.restapi.dto.request.BookRequest;
import gdg.restapi.dto.response.BookResponse;
import gdg.restapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    public BookResponse getById(Long id) {
        return bookRepository.findById(id)
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor()))
                .orElse(null);
    }

    // NPE 방지를 위한 null 반환 대신 Optional 사용
    public Optional<BookResponse> update(Long id, BookRequest request) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            Book updated = bookRepository.update(id, book);
            return new BookResponse(updated.getId(), updated.getTitle(), updated.getAuthor());
        });
    }

    public boolean delete(Long id) {
        return bookRepository.delete(id);
    }
}
