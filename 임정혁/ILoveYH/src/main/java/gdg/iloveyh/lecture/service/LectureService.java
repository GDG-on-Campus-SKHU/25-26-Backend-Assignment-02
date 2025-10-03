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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    public LectureResponse create(LectureRequest request) {
        log.info("강의 생성 요청: title={}", request.getTitle());
        
        Lecture lecture = lectureMapper.toEntity(request);
        Lecture saved = lectureRepository.save(lecture);
        
        log.info("강의 생성 완료: id={}", saved.getId());
        return lectureMapper.toResponse(saved);
    }

    public List<LectureResponse> getAll() {
        log.info("전체 강의 목록 조회 요청");
        
        List<Lecture> lectures = lectureRepository.findAll();
        
        log.info("전체 강의 목록 조회 완료: {}개", lectures.size());
        return lectures.stream()
                .map(lectureMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LectureResponse getById(Long id) {
        log.info("강의 조회 요청: id={}", id);
        
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
        
        log.info("강의 조회 완료: id={}, title={}", lecture.getId(), lecture.getTitle());
        return lectureMapper.toResponse(lecture);
    }

    public LectureResponse update(Long id, LectureRequest request) {
        log.info("강의 수정 요청: id={}", id);
        
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
        
        Lecture updatedLecture = lectureMapper.updateEntity(lecture, request);
        Lecture updated = lectureRepository.update(id, updatedLecture);
        
        log.info("강의 수정 완료: id={}", updated.getId());
        return lectureMapper.toResponse(updated);
    }

    public void delete(Long id) {
        log.info("강의 삭제 요청: id={}", id);
        
        if (!lectureRepository.findById(id).isPresent()) {
            throw new LectureNotFoundException(id);
        }
        
        lectureRepository.delete(id);
        log.info("강의 삭제 완료: id={}", id);
    }
}
