package gdg.restapi.service;

import gdg.restapi.domain.Task;
import gdg.restapi.dto.TaskRequest;
import gdg.restapi.repository.TaskRepository;
import gdg.restapi.repository.TaskRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskRepositoryImpl repositoryImpl;

    public TaskService(final TaskRepository repository, final TaskRepositoryImpl repositoryImpl) {
        this.repository = repository;
        this.repositoryImpl = repositoryImpl;
    }

    public Task create(final TaskRequest request) {
        final Long id = repositoryImpl.nextId();
        final Task task = new Task(id, request.getTitle(), request.getContent());
        repositoryImpl.addIfAbsent(task); // putIfAbsent 활용
        return task;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(final Long id) {
        return repository.findById(id);
    }

    public Optional<Task> updatePartial(final Long id, final TaskRequest request) {
        final Optional<Task> maybeTask = repository.findById(id);
        if (maybeTask.isEmpty()) {
            return Optional.empty();
        }
        final Task task = maybeTask.get();
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            task.setContent(request.getContent());
        }
        repository.save(task);
        return Optional.of(task);
    }

    public boolean delete(final Long id) {
        return repository.deleteById(id);
    }
}
