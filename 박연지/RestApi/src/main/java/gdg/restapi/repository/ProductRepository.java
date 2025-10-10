package gdg.restapi.repository;

import gdg.restapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByProductId(Long productId);
    boolean delete(Long id);
}


