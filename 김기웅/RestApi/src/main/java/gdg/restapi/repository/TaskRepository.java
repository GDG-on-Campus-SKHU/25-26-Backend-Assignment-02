package gdg.restapi.repository;

import gdg.restapi.domain.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    boolean deleteById(Long id);
}
