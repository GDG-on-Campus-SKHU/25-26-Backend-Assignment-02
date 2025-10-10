package gdg.restapi.repository;

import gdg.restapi.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productMap = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public Product save(Product product) {
        Product productToSave = product;

        if (product.getId() == null) {
            productToSave = Product.builder()
                    .id(sequence.incrementAndGet())
                    .productId(product.getProductId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }
        productMap.put(productToSave.getId(), productToSave);
        return productToSave;
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(productMap.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    @Override
    public Optional<Product> findByProductId(Long productId) {
        return productMap.values().stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
    }

    @Override
    public boolean delete(Long id) {
        return productMap.remove(id) != null;
    }
}
