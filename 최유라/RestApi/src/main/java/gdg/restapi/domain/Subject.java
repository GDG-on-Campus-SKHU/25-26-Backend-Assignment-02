package gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // getter(외부에서 변수 데이터 읽어올 때)/setter(외부에서 변수 데이터 수정할 때?) + toString + equals/hashCode 자동 생성
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Subject {
    private Long id; //번호
    private String name; //과목 이름
    private String unit; //학점
    //private 붙이면 클래스 내부에서만 사용가능(외부 접근 차단?), getter/setter 이용해 제한적으로(일부분) 데이터 조회 및 사용 가능함

    public static Subject create(String name, String unit) {
        return new Subject(null, name, unit);
    }
}
