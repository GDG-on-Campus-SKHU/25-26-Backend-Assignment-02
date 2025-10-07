package gdg.restapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRequest {

    @NotBlank(message = "name은 비어 있을 수 없습니다.")
    private String name;

    // 선택: 필요하면 @NotBlank로 강화 가능
    private String category;

    @PositiveOrZero(message = "durationMin은 0 이상이어야 합니다.")
    private Integer durationMin;

    @PositiveOrZero(message = "calories는 0 이상이어야 합니다.")
    private Integer calories;

    private String memo;

    //선택값
    private LocalDate performedAt;
}