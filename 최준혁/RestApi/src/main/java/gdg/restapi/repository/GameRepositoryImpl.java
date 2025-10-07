package gdg.restapi.repository;

import gdg.restapi.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {
    private final Map<Long, Game> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Game save(Game game){
        game.setId(sequence++);
        store.put(game.getId(),game);
        return game;
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Game update(Long id, Game game) {
        store.put(game.getId(), game);
        return game;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
