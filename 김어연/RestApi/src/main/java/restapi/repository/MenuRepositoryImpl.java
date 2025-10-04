package restapi.repository;

import restapi.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final Map<Long, Menu> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Menu save(Menu menu) {
        menu.setId(++sequence);
        store.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Menu update(Long id, Menu menu) {
        store.put(id, menu);
        return menu;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
