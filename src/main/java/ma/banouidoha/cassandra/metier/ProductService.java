package ma.banouidoha.cassandra.metier;

import ma.banouidoha.cassandra.entity.Product;
import ma.banouidoha.cassandra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, BigDecimal price, int quantity) {
        Product product = new Product(UUID.randomUUID(), name, price, quantity);
        return productRepository.save(product);
    }

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(UUID id, String name, BigDecimal price, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}

