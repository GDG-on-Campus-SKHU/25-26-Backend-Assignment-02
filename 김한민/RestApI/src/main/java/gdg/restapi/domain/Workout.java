package gdg.restapi.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true) // 기존 객체에서 일부 필드만 바꿔서 새 객체 만들기 가능
public class Workout {
    private Long id;
    private String name;          // 운동명
    private String category;      // 부위/유형 (chest/cardio 등)
    private Integer durationMin;  // 분
    private Integer calories;     // 칼로리
    private String memo;          // 메모
    private LocalDate performedAt;// 수행 날짜
}
