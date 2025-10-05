package gdg.restapi.service;

import gdg.restapi.domain.Account;
import gdg.restapi.dto.AccountRequest;
import gdg.restapi.dto.AccountResponse;
import gdg.restapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountResponse create(AccountRequest request) {
        Account account = new Account(null, request.getName(), request.getAccountNumber(), LocalDateTime.now().toString());
        Account saved = repository.save(account);
        return new AccountResponse(saved.getId(), saved.getName(), saved.getAccountNumber(), saved.getCreatedDate());
    }

    public List<AccountResponse> getAll() {
        return repository.findAll().stream()
                .map(a -> new AccountResponse(a.getId(), a.getName(), a.getAccountNumber(), a.getCreatedDate()))
                .collect(Collectors.toList());
    }

    public AccountResponse getById(Long id) {
        return repository.findById(id)
                .map(a -> new AccountResponse(a.getId(), a.getName(), a.getAccountNumber(), a.getCreatedDate()))
                .orElse(null);
    }

    public AccountResponse update(Long id, AccountRequest request) {
        return repository.findById(id).map(account -> {
            account.setName(request.getName());
            account.setAccountNumber(request.getAccountNumber());
            Account updated = repository.update(id, account);
            return new AccountResponse(updated.getId(), updated.getName(), updated.getAccountNumber(), updated.getCreatedDate());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
