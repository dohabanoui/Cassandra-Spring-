package ma.banouidoha.cassandra.web;


import ma.banouidoha.cassandra.entity.Product;
import ma.banouidoha.cassandra.metier.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product.getName(), product.getPrice(), product.getQuantity());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        return productService.updateProduct(id, product.getName(), product.getPrice(), product.getQuantity());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
