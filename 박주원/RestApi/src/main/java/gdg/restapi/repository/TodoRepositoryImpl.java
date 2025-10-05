package gdg.restapi.repository;

import gdg.restapi.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Todo update(Long id, Todo todo) {
        store.put(id, todo);
        return todo;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
        sequence = 0L;
    }
}
