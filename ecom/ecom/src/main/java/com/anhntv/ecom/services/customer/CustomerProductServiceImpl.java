package com.anhntv.ecom.services.customer;

import com.anhntv.ecom.dto.ProductDTO;
import com.anhntv.ecom.entities.Product;
import com.anhntv.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> searchProductByTitle(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }
}
