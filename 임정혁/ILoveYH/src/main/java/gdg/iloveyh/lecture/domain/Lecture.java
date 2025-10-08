package gdg.iloveyh.lecture.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {
    private Long id;
    private String title;
    private String description;
    private Long price;
}
