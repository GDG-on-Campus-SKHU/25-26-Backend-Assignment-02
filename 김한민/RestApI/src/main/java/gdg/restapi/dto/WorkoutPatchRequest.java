package gdg.restapi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 부분 수정: null/""는 서비스에서 정리 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPatchRequest {
    private String name;
    private String category;
    private Integer durationMin;
    private Integer calories;
    private String memo;
    private LocalDate performedAt;
}
