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
        return new ArrayList<>(basket.values());
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
