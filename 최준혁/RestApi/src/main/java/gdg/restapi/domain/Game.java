package gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    //  요청하는 유저가 유효한지 검사하기 위한 용도
    private Long userId;
    //  게임 자체의 id 값
    private Long id;
    //  베팅한 칩의 수
    private Long bettingChips;
    //  딜러의 카드 합
    private Long[] dealerCards;
    //  플레이어의 카드 합
    private Long[] playerCards;
    //  플레이어가 이겼는 지 여부 win: 1, draw: 0, lose: -1
    private Integer result;

}
