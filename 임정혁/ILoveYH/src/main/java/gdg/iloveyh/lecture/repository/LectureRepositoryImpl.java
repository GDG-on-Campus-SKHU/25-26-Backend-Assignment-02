package gdg.iloveyh.lecture.repository;

import gdg.iloveyh.lecture.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final Map<Long, Lecture> store = new HashMap<>();
    
    private Long sequence = 0L;

    @Override
    public Lecture save(Lecture lecture) {
        lecture.setId(++sequence);
        store.put(lecture.getId(), lecture);
        return lecture;
    }

    @Override
    public List<Lecture> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Lecture> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Lecture update(Long id, Lecture lecture) {
        lecture.setId(id);
        store.put(id, lecture);
        return lecture;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
