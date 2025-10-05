package gdg.restapi.repository;

import gdg.restapi.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Student save(Student student) {
        student.setId(++sequence);
        store.put(student.getId(), student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Student update(Long id, Student student) {
        store.put(id, student);
        return student;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}