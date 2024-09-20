package com.ecom.project.service.Implementations;

import com.ecom.project.model.Product;
import com.ecom.project.repository.productRepo;
import com.ecom.project.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productServiceImpl implements productService {

    @Autowired
    private productRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }


    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    public Product deleteProduct(int id) {
        repo.deleteById(id);
        return null;
    }

    public Optional<Product> searchProduct(String name) {
        return repo.findByName(name);
    }
}
