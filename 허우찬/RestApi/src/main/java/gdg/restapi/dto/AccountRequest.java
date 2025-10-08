package gdg.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountRequest {
    @NotBlank(message = "이름은 반드시 입력해야 합니다.")
    private String name;
    @NotBlank(message = "계좌번호는 반드시 입력해야 합니다.")
    private String accountNumber;
}
