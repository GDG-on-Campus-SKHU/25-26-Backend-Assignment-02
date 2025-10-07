package gdg.restapi.controller;

import gdg.restapi.domain.Task;
import gdg.restapi.dto.TaskRequest;
import gdg.restapi.dto.TaskResponse;
import gdg.restapi.service.TaskService;
import java.net.URI;
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
        return ResponseEntity.created(URI.create("/api/tasks/" + saved.getId()))
                .body(new TaskResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> list() {
        final List<TaskResponse> body = taskService.findAll().stream().map(TaskResponse::new).toList();
        return ResponseEntity.ok(body);
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
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
