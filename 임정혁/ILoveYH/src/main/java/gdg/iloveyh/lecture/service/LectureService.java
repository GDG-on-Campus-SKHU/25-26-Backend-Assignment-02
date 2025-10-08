package gdg.iloveyh.lecture.service;

import gdg.iloveyh.lecture.domain.Lecture;
import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import gdg.iloveyh.lecture.exception.LectureNotFoundException;
import gdg.iloveyh.lecture.mapper.LectureMapper;
import gdg.iloveyh.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    public LectureResponse create(LectureRequest request) {
        Lecture lecture = lectureMapper.toEntity(request);
        Lecture saved = lectureRepository.save(lecture);
        
        log.info("강의 생성됨 - id: {}, title: '{}'", saved.getId(), saved.getTitle());
        return lectureMapper.toResponse(saved);
    }

    public List<LectureResponse> getAll() {
        List<Lecture> lectures = lectureRepository.findAll();
        
        log.debug("전체 강의 목록 조회됨 - 총 {}개", lectures.size());
        return lectures.stream()
                .map(lectureMapper::toResponse)
                .toList();
    }

    public LectureResponse getById(Long id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
        
        log.debug("강의 조회됨 - id: {}, title: '{}'", lecture.getId(), lecture.getTitle());
        return lectureMapper.toResponse(lecture);
    }

    public LectureResponse update(Long id, LectureRequest request) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
        
        Lecture updatedLecture = lectureMapper.updateEntity(lecture, request);
        Lecture updated = lectureRepository.update(id, updatedLecture);
        
        log.info("강의 수정됨 - id: {}, title: '{}'", updated.getId(), updated.getTitle());
        return lectureMapper.toResponse(updated);
    }

    public void delete(Long id) {
        boolean deleted = lectureRepository.delete(id);
        if (!deleted) {
            throw new LectureNotFoundException(id);
        }
        
        log.info("강의 삭제됨 - id: {}", id);
    }
}
