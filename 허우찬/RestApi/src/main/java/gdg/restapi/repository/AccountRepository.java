package gdg.restapi.repository;

import gdg.restapi.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Account update(Long id, Account account);
    boolean delete(Long id);
}