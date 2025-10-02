package gdg.restapi.dto;

import gdg.restapi.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private Long productId;
    private String name;
    private Long price;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getId(), product.getProductId(), product.getName(), product.getPrice());
    }
}
