package gdg.restapi2.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class Station {
    private Long id;
    private String station;
    private String line;
}