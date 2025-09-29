package gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // getter/setter + toString + equals/hashCode 자동 생성
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Student {
    private Long id;
    private String name;
    private String major;
}