package gdg.iloveyh.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequest {
    private String title;
    private String description;
    private Long price;
}
