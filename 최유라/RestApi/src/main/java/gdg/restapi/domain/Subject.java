package gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // getter/setter + toString + equals/hashCode 자동 생성
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Subject {
    private Long id; //번호
    private String name; //과목 이름
    private String unit; //학점
}