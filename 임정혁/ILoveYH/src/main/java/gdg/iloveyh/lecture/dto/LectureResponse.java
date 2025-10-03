package gdg.iloveyh.lecture.dto;

public record LectureResponse(
    Long id,
    String title,
    String description,
    Long price
) {}
