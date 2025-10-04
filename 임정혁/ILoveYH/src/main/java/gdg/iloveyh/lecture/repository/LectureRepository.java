package gdg.iloveyh.lecture.repository;

import gdg.iloveyh.lecture.domain.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    Lecture save(Lecture lecture);
    List<Lecture> findAll();
    Optional<Lecture> findById(Long id);
    Lecture update(Long id, Lecture lecture);
    boolean delete(Long id);
}
