package gdg.restapi.repository;

import gdg.restapi.domain.Subject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    private final Map<Long, Subject> basket = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Subject save(Subject subject) {
        subject.setId(++sequence);
        basket.put(subject.getId(), subject);
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        return List.copyOf(basket.values()); //basket 내용 복사한 새로운 리스트 생성 후 반환(수정 불가, 단순 조회 리스트)
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return Optional.ofNullable(basket.get(id));
    }

    @Override
    public Subject update(Long id, Subject subject) {
        basket.put(id, subject);
        return subject;
    }

    @Override
    public boolean delete(Long id) {
        return basket.remove(id) != null;
    }
}
