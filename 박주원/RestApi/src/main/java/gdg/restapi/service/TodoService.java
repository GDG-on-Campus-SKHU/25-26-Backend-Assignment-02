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
        Todo todo = new Todo(null, request.getTitle(), request.isCompleted());
        Todo saved = repository.save(todo);
        return new TodoResponse(saved.getId(), saved.getTitle(), saved.isCompleted());
    }

    public List<TodoResponse> getAll() {
        return repository.findAll().stream()
                .map(t -> new TodoResponse(t.getId(), t.getTitle(), t.isCompleted()))
                .collect(Collectors.toList());
    }

    public TodoResponse getById(Long id) {
        return repository.findById(id)
                .map(t -> new TodoResponse(t.getId(), t.getTitle(), t.isCompleted()))
                .orElse(null);
    }

    public TodoResponse update(Long id, TodoRequest request) {
        return repository.findById(id).map(todo -> {
            todo.setTitle(request.getTitle());
            todo.setCompleted(request.isCompleted());
            Todo updated = repository.update(id, todo);
            return new TodoResponse(updated.getId(), updated.getTitle(), updated.isCompleted());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }

}
