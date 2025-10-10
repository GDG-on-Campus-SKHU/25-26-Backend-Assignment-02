package gdg.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountResponse {
    private Long id;
    private String name;
    private String accountNumber;
    private String createdDate;
}
