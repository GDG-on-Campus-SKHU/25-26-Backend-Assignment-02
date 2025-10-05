package gdg.restapi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResponse {
    private Long id;
    private String name;
    private String category;
    private Integer durationMin;
    private Integer calories;
    private String memo;
    private LocalDate performedAt;
}
