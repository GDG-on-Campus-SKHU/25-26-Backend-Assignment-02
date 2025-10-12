package gdg.restapi.service;

import gdg.restapi.domain.Subject;
import gdg.restapi.dto.SubjectRequest;
import gdg.restapi.dto.SubjectResponse;
import gdg.restapi.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 필드 자동 생성자
public class SubjectService {

    private final SubjectRepository repository;

//    public SubjectResponse create(SubjectRequest request) {
//        Subject subject = new Subject(null, request.getName(), request.getUnit());
//        Subject saved = repository.save(subject);
//        return new SubjectResponse(saved.getId(), saved.getName(), saved.getUnit());
//    }

    public SubjectResponse create(SubjectRequest request) {
        Subject subject = Subject.create(request.getName(), request.getUnit());
        Subject saved = repository.save(subject);
        return new SubjectResponse(saved.getId(), saved.getName(), saved.getUnit());
    }

    public List<SubjectResponse> getAll() {
        return repository.findAll().stream()
                .map(s -> new SubjectResponse(s.getId(), s.getName(), s.getUnit()))
                .collect(Collectors.toList());
        //repository의 findAll()실행 후 stream()내용 수행?
    }

    public SubjectResponse getById(Long id) {
        return repository.findById(id)
                .map(s -> new SubjectResponse(s.getId(), s.getName(), s.getUnit()))
                .orElse(null);
    }

    public SubjectResponse update(Long id, SubjectRequest request) {
        return repository.findById(id).map(subject -> {
            subject.setName(request.getName());
            subject.setUnit(request.getUnit());
            Subject updated = repository.update(id, subject);
            return new SubjectResponse(updated.getId(), updated.getName(), updated.getUnit());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
