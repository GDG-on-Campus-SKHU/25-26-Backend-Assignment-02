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
        Workout w = Workout.builder()
                .name(req.getName())
                .category(req.getCategory())
                .durationMin(req.getDurationMin())
                .calories(req.getCalories())
                .memo(req.getMemo())
                .performedAt(req.getPerformedAt())
                .build();
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

    // PUT: 전체 변경(요청으로 덮어쓰기) — POST와 동일하게 sanitize
    public Optional<WorkoutResponse> putUpdate(Long id, WorkoutRequest req) {
        return repo.findById(id).map(found -> {
            Workout updated = found.toBuilder()
                    .name(req.getName())
                    .category(sanitize(req.getCategory()))
                    .durationMin(req.getDurationMin())
                    .calories(req.getCalories())
                    .memo(sanitize(req.getMemo()))
                    .performedAt(req.getPerformedAt())
                    .build();
            return toRes(repo.update(id, updated));
        });
    }

    // PATCH: null 아닌 값만 반영 + 문자열은 "" → null 변환
    public Optional<WorkoutResponse> patchUpdate(Long id, WorkoutPatchRequest req) {
        return repo.findById(id).map(found -> {
            Workout.WorkoutBuilder b = found.toBuilder();
            if (req.getName() != null)        b.name(emptyToNull(req.getName()));
            if (req.getCategory() != null)    b.category(emptyToNull(req.getCategory()));
            if (req.getDurationMin() != null) b.durationMin(req.getDurationMin());
            if (req.getCalories() != null)    b.calories(req.getCalories());
            if (req.getMemo() != null)        b.memo(emptyToNull(req.getMemo()));
            if (req.getPerformedAt() != null) b.performedAt(req.getPerformedAt());
            Workout updated = b.build();
            return toRes(repo.update(id, updated));
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
    // 공백/빈문자열을 null로 정리(POST/PUT 공통
    private String sanitize(String s) {
        return (s == null || s.isBlank()) ? null : s.trim();
    }

    // PATCH 전용: 요청이 왔을 때 "" -> null 로 간주
    private String emptyToNull(String s) {
        return (s != null && s.isBlank()) ? null : sanitize(s);
    }
}
