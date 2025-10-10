package gdg.restapi.repository;

import gdg.restapi.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book update(Long id, Book book);
    boolean delete(Long id);
}
