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

    private final Map<String, Account> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Account save(Account account) {
        if (store.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("이미 존재하는 계좌번호입니다: " + account.getAccountNumber());
        }

        Account saved = Account.builder()
                .id(++sequence)
                .name(account.getName())
                .accountNumber(account.getAccountNumber())
                .createdDate(account.getCreatedDate())
                .build();

        store.put(saved.getAccountNumber(), saved);
        return saved;
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Account> findById(Long id) {
        return store.values().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public Account update(Long id, Account updated) {
        Optional<Account> existingOpt = findById(id);
        if (existingOpt.isEmpty()) return null;

        Account existing = existingOpt.get();

        if (!existing.getAccountNumber().equals(updated.getAccountNumber()) &&
                store.containsKey(updated.getAccountNumber())) {
            throw new IllegalArgumentException("이미 존재하는 계좌번호입니다: " + updated.getAccountNumber());
        }

        store.remove(existing.getAccountNumber());
        store.put(updated.getAccountNumber(), updated);

        return updated;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Account> existingOpt = findById(id);
        if (existingOpt.isEmpty()) return false;

        store.remove(existingOpt.get().getAccountNumber());
        return true;
    }
}
