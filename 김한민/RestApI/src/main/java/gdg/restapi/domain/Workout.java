package gdg.restapi.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Workout {
    private Long id;
    private String name;          // 운동명
    private String category;      // 부위/유형 (chest/cardio 등)
    private Integer durationMin;  // 분
    private Integer calories;     // 칼로리
    private String memo;          // 메모
    private LocalDate performedAt;// 수행 날짜
}