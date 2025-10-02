package gdg.iloveyh.lecture.repository;

import gdg.iloveyh.lecture.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final Map<Long, Lecture> store = new ConcurrentHashMap<>();
    
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public Lecture save(Lecture lecture) {
        Long newId = sequence.incrementAndGet();
        lecture.setId(newId);
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
