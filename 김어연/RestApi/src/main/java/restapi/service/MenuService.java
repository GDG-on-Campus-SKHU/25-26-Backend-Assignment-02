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
        Menu menu = new Menu(null, request.getName(), request.getPrice());
        Menu saved = repository.save(menu);
        return new MenuResponse(saved.getId(), saved.getName(), saved.getPrice());
    }

    public List<MenuResponse> getAll() {
        return repository.findAll().stream()
                .map(m -> new MenuResponse(m.getId(), m.getName(), m.getPrice()))
                .toList();
    }

    public MenuResponse getById(Long id) {
        return repository.findById(id)
                .map(m -> new MenuResponse(m.getId(), m.getName(), m.getPrice()))
                .orElse(null);
    }

    public MenuResponse update(Long id, MenuRequest request) {
        return repository.findById(id).map(menu -> {
            menu.setName(request.getName());
            menu.setPrice(request.getPrice());
            Menu updated = repository.update(id, menu);
            return new MenuResponse(updated.getId(), updated.getName(), updated.getPrice());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}