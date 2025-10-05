package gdg.restapi2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // getter/setter + toString + equals/hashCode 자동 생성
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Station {
    private Long id;
    private String station;
    private String line;
}