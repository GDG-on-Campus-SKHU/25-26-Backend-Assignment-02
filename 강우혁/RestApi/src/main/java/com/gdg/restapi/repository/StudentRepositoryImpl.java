package com.gdg.restapi.repository;

import org.springframework.stereotype.Repository;
import com.gdg.restapi.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Student save(Student student){
        student.setId(++sequence);
        store.put(student.getId(), student);
        return student;
    }
    @Override
    public List<Student> findAll(){
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Student> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Student update(Long id, Student student){
        store.put(id, student);
        return student;
    }
    @Override
    public boolean delete(Long id){
        return store.remove(id) != null;
    }


}
