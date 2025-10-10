package gdg.restapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; //기본 생성자 자동생성
import lombok.AllArgsConstructor; //파라미터 있는 생성자 자동생성

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Long id;
    private String content;
}
