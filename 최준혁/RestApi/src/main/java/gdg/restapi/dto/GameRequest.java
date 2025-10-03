package gdg.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {
//  현재 접속중인 플레이어의 id 전송
    private Long userId;
//  현재 접속중인 플레이어의 베팅 칩 전송
    private Long bettingChips;
}
