package gdg.restapi.repository;

import gdg.restapi.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Map<Long, Book> bookMap = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        bookMap.put(book.getId(), book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(bookMap.get(id));
    }

    @Override
    public Book update(Long id, Book book) {
        bookMap.put(id, book);
        return book;
    }

    @Override
    public boolean delete(Long id) {
        return bookMap.remove(id) != null;
    }
}
