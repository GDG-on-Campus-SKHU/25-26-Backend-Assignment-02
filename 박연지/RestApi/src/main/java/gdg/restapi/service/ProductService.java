package gdg.restapi.service;

import gdg.restapi.domain.Product;
import gdg.restapi.dto.ProductResponseDto;
import gdg.restapi.dto.ProductSaveRequestDto;
import gdg.restapi.dto.ProductUpdateRequestDto;
import gdg.restapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto create(ProductSaveRequestDto requestDto) {
        Product product = Product.builder()
                .productId(requestDto.getProductId())
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .build();

        return ProductResponseDto.from(productRepository.save(product));
    }

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public ProductResponseDto findById(Long id) {
        return productRepository.findById(id)
                .map(ProductResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id=" + id));
    }

    public ProductResponseDto findByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .map(ProductResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. productId=" + productId));
    }


    public ProductResponseDto update(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id=" + id));

        product.update(requestDto.getName(), requestDto.getPrice());
        return ProductResponseDto.from(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.delete(id)) {
            throw new IllegalArgumentException("삭제할 상품이 없습니다. id=" + id);
        }
    }
}
