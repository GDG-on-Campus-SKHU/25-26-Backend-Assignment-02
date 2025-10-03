package gdg.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    //  유저 아이디
    private Long id;
    //  유저 닉네임
    private String name;
    //  유저 최고 점수 기록
    private Long record;
    //  유저 재화
    private Long dollar;
    //  유저 스킨 보유
    private boolean[] cardSkins;
}
