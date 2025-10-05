package gdg.restapi.repository;


import gdg.restapi.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final Map<Long, Account> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Account save(Account account) {
        account.setId(++sequence);
        store.put(account.getId(), account);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Account update(Long id, Account account) {
        account.setId(id);
        store.put(id, account);
        return account;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
