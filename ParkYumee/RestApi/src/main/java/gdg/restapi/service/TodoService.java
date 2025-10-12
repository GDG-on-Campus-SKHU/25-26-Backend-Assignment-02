package gdg.restapi.service;

import gdg.restapi.domain.Todo;
import gdg.restapi.dto.TodoRequest;
import gdg.restapi.dto.TodoResponse;
import gdg.restapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public TodoResponse create(TodoRequest request) {
        Todo todo = Todo.builder()
                .title(request.title())
                .content(request.content())
                .isWeekly(request.isWeekly())
                .build();

        Todo saved = repository.save(todo);

        return TodoResponse.builder()
                .id(saved.getId())
                .todoTitle(saved.getTitle())
                .todoContent(saved.getContent())
                .isWeekly(saved.isWeekly())
                .build();

    }

    //    public List<TodoResponse> getAll() {
//        return repository.findAll().stream()
//                .map(t -> new TodoResponse(t.getId(),t.getTitle(),t.getContent(),t.isWeekly()))
//                .collect(Collectors.toList());
//    }
    public List<TodoResponse> getAll() {
        return repository.findAll().stream()
                .map(t -> TodoResponse.builder()
                        .id(t.getId())
                        .todoTitle(t.getTitle())
                        .todoContent(t.getContent())
                        .isWeekly(t.isWeekly())
                        .build())
                .toList();
    }

    //    public TodoResponse getById(Long id) {
//        return repository.findById(id)
//                .map(t -> new TodoResponse(t.getId(),t.getTitle(),t.getContent(),t.isWeekly()))
//                .orElse(null);
//    }
    public TodoResponse getById(Long id) {
        return repository.findById(id)
                .map(t -> TodoResponse.builder()
                        .id(t.getId())
                        .todoTitle(t.getTitle())
                        .todoContent(t.getContent())
                        .isWeekly(t.isWeekly())
                        .build())
                .orElse(null);
    }

//    public TodoResponse update(Long id, TodoRequest request) {
//        return repository.findById(id).map(todo -> {
//            todo.setTitle(request.getTitle());
//            todo.setContent(request.getContent());
//

//            Todo updated = repository.update(id, todo);

//            return new TodoResponse(updated.getId(), updated.getTitle(), updated.getContent(), updated.isWeekly());
//        }).orElse(null);
//    }

    public TodoResponse update(Long id, TodoRequest request) {
        return repository.findById(id)
                .map(t ->
                {Todo todo = Todo.builder()
                        .id(t.getId())
                        .title(request.title())
                        .content(request.content())
                        .isWeekly(request.isWeekly())
                        .build();

                    Todo update = repository.update(id, todo);

                    return TodoResponse.builder()
                            .id(update.getId())
                            .todoTitle(update.getTitle())
                            .todoContent(update.getContent())
                            .isWeekly(update.isWeekly())
                            .build();
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
