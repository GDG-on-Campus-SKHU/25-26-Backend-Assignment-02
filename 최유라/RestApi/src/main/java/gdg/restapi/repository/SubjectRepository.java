package gdg.restapi.repository;

import gdg.restapi.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    Subject save(Subject subject);
    List<Subject> findAll();
    Optional<Subject> findById(Long id);
    Subject update(Long id, Subject subject);
    boolean delete(Long id);
}