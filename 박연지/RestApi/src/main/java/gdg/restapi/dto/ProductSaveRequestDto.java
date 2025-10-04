package gdg.restapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private  Long productId;
    private String name;
    private Long price;
}
