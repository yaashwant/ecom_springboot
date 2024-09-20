package com.ecom.project.service;

import com.ecom.project.model.Product;

import java.util.List;

public interface productService {

    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(int id);
}

