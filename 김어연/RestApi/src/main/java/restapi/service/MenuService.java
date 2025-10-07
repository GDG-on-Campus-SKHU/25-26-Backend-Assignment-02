package restapi.service;

import restapi.domain.Menu;
import restapi.dto.MenuRequest;
import restapi.dto.MenuResponse;
import restapi.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MenuService {

    private final MenuRepository repository;

    public MenuResponse create(MenuRequest request) {
        Menu menu = Menu.create(request.getName(), request.getPrice());
        Menu saved = repository.save(menu);
        return MenuResponse.from(saved);
    }

    public List<MenuResponse> getAll() {
        return repository.findAll().stream()
                .map(MenuResponse::from)
                .toList();
    }

    public MenuResponse getById(Long id) {
        return repository.findById(id)
                .map(MenuResponse::from)
                .orElse(null);
    }

    public MenuResponse update(Long id, MenuRequest request) {
        return repository.findById(id).map(menu -> {
            menu.setName(request.getName());
            menu.setPrice(request.getPrice());
            Menu updated = repository.update(id, menu);
            return MenuResponse.from(updated);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}