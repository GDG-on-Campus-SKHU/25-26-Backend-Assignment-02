package gdg.restapi.controller;

import gdg.restapi.domain.Task;
import gdg.restapi.dto.TaskRequest;
import gdg.restapi.dto.TaskResponse;
import gdg.restapi.service.TaskService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody final TaskRequest request) {
        final Task saved = taskService.create(request);
        return ResponseEntity
                .created(java.net.URI.create("/api/tasks/" + saved.getId()))
                .body(new TaskResponse(saved));
    }

    @GetMapping
    public List<TaskResponse> list() {
        return taskService.findAll()
                .stream()
                .map(TaskResponse::new)
                .toList(); // 불변 리스트 반환
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> get(@PathVariable final Long id) {
        return taskService.findById(id)
                .map(task -> ResponseEntity.ok(new TaskResponse(task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> patch(@PathVariable final Long id, @RequestBody final TaskRequest request) {
        return taskService.updatePartial(id, request)
                .map(task -> ResponseEntity.ok(new TaskResponse(task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        final boolean removed = taskService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
