package gdg.restapi.service;

import gdg.restapi.domain.User;
import gdg.restapi.dto.UserRequest;
import gdg.restapi.dto.UserResponse;
import gdg.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse create(UserRequest request) {
        User user= new User(null, request.getName(), (long)0, (long)0, new boolean[]{false});
        User saved = repository.save(user);
        return new UserResponse(saved.getId(), saved.getName(), saved.getRecord(), saved.getDollar(), saved.getCardSkins());
    }

    public List<UserResponse> getAll() {
    //  유저 중 최고기록으로 정렬 후 상위 50위만 반환
        return repository.findAll().stream()
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getRecord(), u.getDollar(), u.getCardSkins()))
                .sorted((a, b) -> (int) (a.getRecord() - b.getRecord()))
                .limit(50)
                .toList();
    }

    public UserResponse getById(Long id) {
        return repository.findById(id)
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getRecord(), u.getDollar(), u.getCardSkins()))
                .orElse(null);
    }

    public UserResponse update(Long id, UserRequest request) {
        return repository.findById(id)
                .map(u -> {
                    u.setName(request.getName());
                    return new UserResponse(u.getId(), u.getName(), u.getRecord(), u.getDollar(), u.getCardSkins());
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
