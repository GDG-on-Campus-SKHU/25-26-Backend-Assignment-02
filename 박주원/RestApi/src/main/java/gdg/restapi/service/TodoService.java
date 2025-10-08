package gdg.restapi.service;

import gdg.restapi.domain.Todo;
import gdg.restapi.dto.TodoRequest;
import gdg.restapi.dto.TodoResponse;
import gdg.restapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public TodoResponse create(TodoRequest request) {
        Todo todo = Todo.create(request.getTitle(), request.isCompleted());
        Todo saved = repository.save(todo);
        return TodoResponse.from(saved);
    }

    public List<TodoResponse> getAll() {
        return List.copyOf(
                repository.findAll().stream()
                        .map(TodoResponse::from)
                        .toList()
        );
    }

    public TodoResponse getById(Long id) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id " + id));
        return TodoResponse.from(todo);
    }

    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id " + id));
        todo.update(request.getTitle(), request.isCompleted());  
        Todo updated = repository.update(id, todo);
        return TodoResponse.from(updated);
    }

    public void delete(Long id) {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new IllegalArgumentException("Todo not found with id " + id);
        }
    }
}
