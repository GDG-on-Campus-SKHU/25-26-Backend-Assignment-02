package gdg.restapi.controller;

import gdg.restapi.dto.ProductResponseDto;
import gdg.restapi.dto.ProductSaveRequestDto;
import gdg.restapi.dto.ProductUpdateRequestDto;
import gdg.restapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductSaveRequestDto requestDto) {
        return ResponseEntity.ok(productService.create(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequestDto requestDto) {
        return ResponseEntity.ok(productService.update(id, requestDto));
    }

    @GetMapping("/product-id/{productId}")
    public ResponseEntity<ProductResponseDto> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("삭제 성공!");
    }
}
