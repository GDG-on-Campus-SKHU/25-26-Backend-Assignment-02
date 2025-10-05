package gdg.restapi.repository;

import gdg.restapi.domain.Workout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class WorkoutRepositoryImpl implements WorkoutRepository {
    private final Map<Long, Workout> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Workout save(Workout workout) {
        workout.setId(++sequence);
        store.put(workout.getId(), workout);
        return workout;
    }

    @Override
    public List<Workout> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Workout> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Workout update(Long id, Workout workout) {
        store.put(id, workout);
        return workout;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}