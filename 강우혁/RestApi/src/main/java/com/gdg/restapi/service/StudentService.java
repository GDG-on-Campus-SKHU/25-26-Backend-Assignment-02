package com.gdg.restapi.service;

import com.gdg.restapi.domain.Student;
import com.gdg.restapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gdg.restapi.dto.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public StudentResponse create(StudentRequest request){
        Student student = Student.builder()
                .id(null)
                .name(request.name())
                .major(request.major())
                .build();
        Student saved = repository.save(student);
        return new StudentResponse(saved.getId(), saved.getName(), saved.getMajor());
    }
    public List<StudentResponse> getAll() {
        return repository.findAll().stream()
                .map(student->new StudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getMajor()))
                .collect(Collectors.toList());
    }
    public StudentResponse getById(Long id) {
        return repository.findById(id).map(student->
                        new StudentResponse(
                                student.getId(),
                                student.getName(),
                                student.getMajor()))
                .orElse(null);
    }
    public StudentResponse update(Long id, StudentRequest request) {
        return repository.findById(id).map(student->{
            student.setName(request.name());
            student.setMajor(request.major());
            Student updated = repository.update(id, student);
            return new StudentResponse(updated.getId(), updated.getName(), updated.getMajor());
        }).orElse(null);
    }
    public boolean delete(Long userId) {
        return repository.delete(userId);
    }
}
