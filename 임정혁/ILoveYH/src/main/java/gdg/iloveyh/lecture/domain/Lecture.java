package gdg.iloveyh.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    private Long id;
    private String title;
    private String description;
    private Long price;
}
