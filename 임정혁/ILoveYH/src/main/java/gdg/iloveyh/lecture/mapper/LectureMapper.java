package gdg.iloveyh.lecture.mapper;

import gdg.iloveyh.lecture.domain.Lecture;
import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {
    
    public Lecture toEntity(LectureRequest request) {
        return new Lecture(
            null,
            request.getTitle(),
            request.getDescription(),
            request.getPrice()
        );
    }
    
    public LectureResponse toResponse(Lecture lecture) {
        return new LectureResponse(
            lecture.getId(),
            lecture.getTitle(),
            lecture.getDescription(),
            lecture.getPrice()
        );
    }
    
    public void updateEntity(Lecture lecture, LectureRequest request) {
        lecture.setTitle(request.getTitle());
        lecture.setDescription(request.getDescription());
        lecture.setPrice(request.getPrice());
    }
}

