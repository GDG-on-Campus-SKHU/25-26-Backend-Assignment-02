package gdg.restapi.repository;

import gdg.restapi.domain.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    Todo update(Long id, Todo todo);
    boolean delete(Long id);
}
