package gdg.restapi.repository;

import gdg.restapi.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student update(Long id, Student student);
    boolean delete(Long id);
}
