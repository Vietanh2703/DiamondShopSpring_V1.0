package com.anhntv.ecom.controller.customer;


import com.anhntv.ecom.dto.ProductDTO;
import com.anhntv.ecom.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> dtos = customerProductService.getAllProducts();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@PathVariable String name) {
        List<ProductDTO> dtos = customerProductService.searchProductByTitle(name);
        return ResponseEntity.ok(dtos);
    }
}