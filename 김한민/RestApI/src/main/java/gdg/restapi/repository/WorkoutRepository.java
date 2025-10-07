package gdg.restapi.repository;

import gdg.restapi.domain.Workout;

import java.util.List;
import java.util.Optional;


public interface WorkoutRepository {
    Workout save(Workout workout);
    List<Workout> findAll();
    Optional<Workout> findById(Long id);
    Workout update(Long id, Workout workout);
    boolean delete(Long id);
}
