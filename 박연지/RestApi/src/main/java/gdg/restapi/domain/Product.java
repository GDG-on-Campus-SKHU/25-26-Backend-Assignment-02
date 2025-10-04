package gdg.restapi.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    private Long id;
    private Long productId;
    private String name;
    private Long price;

    public void update(String name, Long price) {
        if (name != null) {
            this.name = name;
        }
        if (price != null) {
            this.price = price;
        }
    }
}
