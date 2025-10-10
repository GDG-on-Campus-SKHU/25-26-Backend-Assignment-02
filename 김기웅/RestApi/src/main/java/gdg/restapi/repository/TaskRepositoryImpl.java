package gdg.restapi.repository;

import gdg.restapi.domain.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Long, Task> tasksById = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Long nextId() {
        return sequence.getAndIncrement();
    }

    @Override
    public Task save(final Task task) {
        tasksById.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(final Long id) {
        return Optional.ofNullable(tasksById.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasksById.values());
    }

    @Override
    public boolean deleteById(final Long id) {
        return tasksById.remove(id) != null;
    }

    public boolean addIfAbsent(final Task task) {
        return tasksById.putIfAbsent(task.getId(), task) == null;
    }
}
