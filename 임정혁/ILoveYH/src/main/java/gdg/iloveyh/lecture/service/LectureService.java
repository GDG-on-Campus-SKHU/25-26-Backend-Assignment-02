package gdg.iloveyh.lecture.service;

import gdg.iloveyh.lecture.domain.Lecture;
import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import gdg.iloveyh.lecture.exception.LectureNotFoundException;
import gdg.iloveyh.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureResponse create(LectureRequest request) {
        Lecture lecture = new Lecture(null, request.getTitle(), request.getDescription(), request.getPrice());
        Lecture saved = lectureRepository.save(lecture);
        return new LectureResponse(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPrice());
    }

    public List<LectureResponse> getAll() {
        return lectureRepository.findAll().stream()
                .map(lecture -> new LectureResponse(lecture.getId(), lecture.getTitle(), lecture.getDescription(), lecture.getPrice()))
                .collect(Collectors.toList());
    }

    public LectureResponse getById(Long id) {
        return lectureRepository.findById(id)
                .map(lecture -> new LectureResponse(lecture.getId(), lecture.getTitle(), lecture.getDescription(), lecture.getPrice()))
                .orElseThrow(() -> new LectureNotFoundException(id));
    }

    public LectureResponse update(Long id, LectureRequest request) {
        return lectureRepository.findById(id).map(lecture -> {
            lecture.setTitle(request.getTitle());
            lecture.setDescription(request.getDescription());
            lecture.setPrice(request.getPrice());
            Lecture updated = lectureRepository.update(id, lecture);
            return new LectureResponse(updated.getId(), updated.getTitle(), updated.getDescription(), updated.getPrice());
        }).orElseThrow(() -> new LectureNotFoundException(id));
    }

    public void delete(Long id) {
        if (!lectureRepository.findById(id).isPresent()) {
            throw new LectureNotFoundException(id);
        }
        lectureRepository.delete(id);
    }
}

