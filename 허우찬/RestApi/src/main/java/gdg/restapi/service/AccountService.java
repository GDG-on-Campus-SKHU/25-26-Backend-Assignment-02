package gdg.restapi.service;

import gdg.restapi.domain.Account;
import gdg.restapi.dto.AccountRequest;
import gdg.restapi.dto.AccountResponse;
import gdg.restapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountResponse create(AccountRequest request) {
        Account account = Account.builder()
                .id(null)
                .name(request.getName())
                .accountNumber(request.getAccountNumber())
                .createdDate(LocalDate.now())
                .build();

        Account saved = repository.save(account);
        return new AccountResponse(saved.getId(), saved.getName(), saved.getAccountNumber(),
                saved.getCreatedDate().toString());
    }

    public List<AccountResponse> getAll() {
        return repository.findAll().stream()
                .map(a -> new AccountResponse(
                        a.getId(),
                        a.getName(),
                        a.getAccountNumber(),
                        a.getCreatedDate().toString()
                ))
                .toList();
    }

    public AccountResponse getById(Long id) {
        return repository.findById(id)
                .map(a -> new AccountResponse(
                        a.getId(),
                        a.getName(),
                        a.getAccountNumber(),
                        a.getCreatedDate().toString()
                ))
                .orElse(null);
    }

    public AccountResponse update(Long id, AccountRequest request) {
        return repository.findById(id).map(existing -> {
            Account updated = Account.builder()
                    .id(existing.getId())
                    .name(request.getName())
                    .accountNumber(request.getAccountNumber())
                    .createdDate(existing.getCreatedDate())
                    .build();

            Account saved = repository.update(id, updated);

            String createdDateStr = saved.getCreatedDate().toString();

            return new AccountResponse(
                    saved.getId(),
                    saved.getName(),
                    saved.getAccountNumber(),
                    createdDateStr
            );
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
