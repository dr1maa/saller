package com.example.saller.services;


import com.example.saller.models.Product;
import com.example.saller.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
        if(title !=null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProducts(Product product) {
       log.info("Saving new {}",product);
       productRepository.save(product);
    }

    public void deleteProducts(Long id) {
     productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
