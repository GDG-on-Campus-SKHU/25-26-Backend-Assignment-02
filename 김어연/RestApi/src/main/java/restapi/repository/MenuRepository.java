package restapi.repository;

import java.awt.*;
import java.util.Optional;
import java.util.List;
import restapi.domain.Menu;

public interface MenuRepository {
    Menu save(Menu menu);
    List<Menu> findAll();
    Optional<Menu> findById(Long id);
    Menu update(Long id, Menu menu);
    boolean delete(Long id);
}
