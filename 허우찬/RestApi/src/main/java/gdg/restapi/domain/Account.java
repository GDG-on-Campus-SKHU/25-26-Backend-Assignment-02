package gdg.restapi.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Account {
    private final Long id;
    private final String name;
    private final String accountNumber;
    private final LocalDate createdDate;

    @Builder
    public Account(Long id, String name, String accountNumber, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.createdDate = createdDate;
    }
}
