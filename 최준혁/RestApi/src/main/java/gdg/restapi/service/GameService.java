package gdg.restapi.service;

import gdg.restapi.domain.Game;
import gdg.restapi.dto.GameRequest;
import gdg.restapi.dto.GameResponse;
import gdg.restapi.repository.GameRepository;
import gdg.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;
    private final UserRepository userRepository;

    public GameResponse create(GameRequest request) {
//      요청받은 유저의 id가 유저 정보에 존재하지 않는 경우 예외 발생
        userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

//      딜러와 플레이어의 카드를 각각 2장 씩 1~11 중 무작위로 추출 후 저장 (JQK(10)는 확률이 더 높지만...)
//      또 편의를 위해 hit 기능 없이 단판으로 구현
        Long[] dealerCards = {((long) (Math.random() * 10)) + 1, ((long) (Math.random() * 10)) + 1};
        Long[] temp = {dealerCards[0], null};
        Long[] playerCards = {((long) (Math.random() * 10)) + 1, ((long) (Math.random() * 10)) + 1};

//      딜러와 플레이어의 카드 합
        Long dealerSum = dealerCards[0] + dealerCards[1];
        Long playerSum = playerCards[0] + playerCards[1];

//      만약 딜러 및 플레이어의 카드 합이 11을 넘지 않고 A를 포함하는 경우 10을 더해 A를 1이 아닌 11로 취급
        if(dealerSum <= 11){
            if(dealerCards[0] == 1)
                dealerCards[0] += 10;
            if(dealerCards[1] == 1)
                dealerCards[1] += 10;
        }
        if(playerSum <= 11){
            if(playerCards[0] == 1)
                playerCards[0] += 10;
            if(playerCards[1] == 1)
                playerCards[1] += 10;
        }

//      승패 여부 참거짓 값
        Integer result = 0;

        if(dealerSum.equals((long)21) || dealerSum > playerSum) {
//          만약 딜러가 21이거나 플레이어보다 큰 경우 패배
            result = -1;
        } else {
//          플레이어가 합이 높다면 승리(이때 딜러의 최대 합은 20까지 이기 때문에 플레이어 합 21에 대한 경우는 쓸 필요 없음)
            if (playerSum > dealerSum)
                result = 1;
//          같다면 무승부
            if (playerSum.equals(dealerSum))
                result = 0;
        }

//      게임 내용 저장
        Game game = new Game(request.getUserId(), null, request.getBettingChips(), dealerCards, playerCards, result);
        Game saved = repository.save(game);
//      프론트에 반환하는 값 중 딜러의 두 번째 카드와 승패 여부는 숨겨서 반환
        return new GameResponse(saved.getUserId(), saved.getId(), saved.getBettingChips(), temp, saved.getPlayerCards(), 0);
    }

    public List<GameResponse> getAll() {
        return repository.findAll().stream()
//              필터로 승리한 경우만, 베팅 칩으로 정렬 후, 상위 50위에 속하는 게임만 반환
                .map(g -> new GameResponse(g.getUserId(), g.getId(), g.getBettingChips(), g.getDealerCards(), g.getPlayerCards(), g.getResult()))
                .filter(g -> g.getResult().equals(1))
                .sorted((a, b) -> (int) (a.getBettingChips() - b.getBettingChips()))
                .limit(50)
                .toList();
    }

    public GameResponse getById(Long id) {
        return repository.findById(id)
                .map(g -> new GameResponse(g.getUserId(), g.getId(), g.getBettingChips(), g.getDealerCards(), g.getPlayerCards(), g.getResult()))
                .orElse(null);
    }

    public GameResponse update(Long id, GameRequest request) {
//      요청받은 유저의 id가 유저 정보에 존재하지 않는 경우 예외 발생
        userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

//      id값과 베팅 칩으로 베팅칩만 덮어쓰기
        return repository.findById(id)
                .filter(g -> g.getBettingChips() <= request.getBettingChips())
                .map(g -> {
                    g.setBettingChips(request.getBettingChips());
                    return new GameResponse(g.getUserId(), g.getId(), g.getBettingChips(), g.getDealerCards(), g.getPlayerCards(), g.getResult());
                })
                .orElseThrow(() -> new IllegalArgumentException("베팅은 이전 베팅보다 커야 합니다."));
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
