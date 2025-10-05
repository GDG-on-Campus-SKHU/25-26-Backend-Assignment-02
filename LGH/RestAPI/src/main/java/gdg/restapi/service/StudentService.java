package gdg.restapi.service;

import gdg.restapi.domain.Student;
import gdg.restapi.dto.StudentRequest;
import gdg.restapi.dto.StudentResponse;
import gdg.restapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 필드 자동 생성자
public class StudentService {

    private final StudentRepository repository;

    public StudentResponse create(StudentRequest request) {
        Student student = new Student(null, request.getName(), request.getMajor());
        Student saved = repository.save(student);
        return new StudentResponse(saved.getId(), saved.getName(), saved.getMajor());
    }

    public List<StudentResponse> getAll() {
        return repository.findAll().stream()
                .map(s -> new StudentResponse(s.getId(), s.getName(), s.getMajor()))
                .collect(Collectors.toList());
    }

    public StudentResponse getById(Long id) {
        return repository.findById(id)
                .map(s -> new StudentResponse(s.getId(), s.getName(), s.getMajor()))
                .orElse(null);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        return repository.findById(id).map(student -> {
            student.setName(request.getName());
            student.setMajor(request.getMajor());
            Student updated = repository.update(id, student);
            return new StudentResponse(updated.getId(), updated.getName(), updated.getMajor());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}