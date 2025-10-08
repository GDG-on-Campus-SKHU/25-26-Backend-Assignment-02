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
        return repository.findAll().stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

    public TodoResponse getById(Long id) {
        return repository.findById(id)
                .map(TodoResponse::from)
                .orElse(null);
    }

    public TodoResponse update(Long id, TodoRequest request) {
        return repository.findById(id).map(todo -> {
            todo.update(request.getTitle(), request.isCompleted());
            Todo updated = repository.update(id, todo);
            return TodoResponse.from(updated);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
