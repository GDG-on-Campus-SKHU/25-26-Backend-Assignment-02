package gdg.iloveyh.lecture.mapper;

import gdg.iloveyh.lecture.domain.Lecture;
import gdg.iloveyh.lecture.dto.LectureRequest;
import gdg.iloveyh.lecture.dto.LectureResponse;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {
    
    public Lecture toEntity(LectureRequest request) {
        return Lecture.builder()
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .build();
    }
    
    public LectureResponse toResponse(Lecture lecture) {
        return new LectureResponse(
            lecture.getId(),
            lecture.getTitle(),
            lecture.getDescription(),
            lecture.getPrice()
        );
    }
    
    public Lecture updateEntity(Lecture lecture, LectureRequest request) {
        return Lecture.builder()
                .id(lecture.getId())
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .build();
    }
}

