package gdg.restapi.repository;

import gdg.restapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public User save(User user){
        user.setId(sequence++);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public User update(Long id, User user) {
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
