package com.example.productservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product, String role) {
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only ADMIN can create products");
        }
        return productRepository.save(product);
    }

    public Product update(Long id, Product newData) {
        Product p = productRepository.findById(id).orElseThrow();
        p.setName(newData.getName());
        p.setBrand(newData.getBrand());
        p.setPrice(newData.getPrice());
        p.setDescription(newData.getDescription());
        p.setQuantity(newData.getQuantity());
        return productRepository.save(p);
    }

    public void delete(Long id, String role) {
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only ADMIN can delete products");
        }
        productRepository.deleteById(id);
    }
}