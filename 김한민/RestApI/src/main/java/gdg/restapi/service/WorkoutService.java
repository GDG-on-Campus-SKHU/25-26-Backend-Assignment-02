package gdg.restapi.service;

import gdg.restapi.domain.Workout;
import gdg.restapi.dto.WorkoutRequest;
import gdg.restapi.dto.WorkoutPatchRequest;
import gdg.restapi.dto.WorkoutResponse;
import gdg.restapi.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor // final 필드 자동 생성자
public class WorkoutService {

    private final WorkoutRepository repo;

    public WorkoutResponse create(WorkoutRequest req) {
        Workout w = new Workout(
                null,
                req.getName(),
                req.getCategory(),
                req.getDurationMin(),
                req.getCalories(),
                req.getMemo(),
                req.getPerformedAt()
        );
        return toRes(repo.save(w));
    }

    public List<WorkoutResponse> getAll() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(
                        Workout::getPerformedAt,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ).reversed())
                .map(this::toRes)
                .collect(Collectors.toList());
    }

    public Optional<WorkoutResponse> getById(Long id) {
        return repo.findById(id).map(this::toRes);
    }

    public Optional<WorkoutResponse> putUpdate(Long id, WorkoutRequest req) {
        return repo.findById(id).map(found -> {
            found.setName(req.getName());
            found.setCategory(req.getCategory());
            found.setDurationMin(req.getDurationMin());
            found.setCalories(req.getCalories());
            found.setMemo(req.getMemo());
            found.setPerformedAt(req.getPerformedAt());
            return toRes(repo.update(id, found));
        });
    }

    /** PATCH: null 아닌 값만 반영 */
    public Optional<WorkoutResponse> patchUpdate(Long id, WorkoutPatchRequest req) {
        return repo.findById(id).map(found -> {
            if (req.getName() != null)        found.setName(req.getName());
            if (req.getCategory() != null)    found.setCategory(req.getCategory());
            if (req.getDurationMin() != null) found.setDurationMin(req.getDurationMin());
            if (req.getCalories() != null)    found.setCalories(req.getCalories());
            if (req.getMemo() != null)        found.setMemo(req.getMemo());
            if (req.getPerformedAt() != null) found.setPerformedAt(req.getPerformedAt());
            return toRes(repo.update(id, found));
        });
    }

    public boolean delete(Long id) {
        return repo.delete(id);
    }


    private WorkoutResponse toRes(Workout w) {
        return new WorkoutResponse(
                w.getId(),
                w.getName(),
                w.getCategory(),
                w.getDurationMin(),
                w.getCalories(),
                w.getMemo(),
                w.getPerformedAt()
        );
    }
}