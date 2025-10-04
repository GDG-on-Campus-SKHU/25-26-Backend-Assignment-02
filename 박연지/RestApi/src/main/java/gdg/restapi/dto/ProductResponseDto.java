package gdg.restapi.dto;

import gdg.restapi.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {
    private Long id;
    private Long productId;
    private String name;
    private Long price;

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}

