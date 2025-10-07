package gdg.restapi.repository;

import gdg.restapi.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
//  베팅 금액과 id를 전달받아 저장 후 dealer의 첫 번째 카드만 반환
    Game save(Game game);
//  베팅 금액과 id를 전달 받으면 bettingChips에 더해줌
    Game update(Long id, Game game);
//  이긴 게임 중 베팅 칩 상위 50위의 게임을 반환
    List<Game> findAll();
//  id 값으로 해당 게임을 불러옴
    Optional<Game> findById(Long id);
//  해당 게임 기록을 삭제
    boolean delete(Long id);
}
