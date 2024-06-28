package com.anhntv.ecom.controller.manager;

import com.anhntv.ecom.dto.ProductDTO;
import com.anhntv.ecom.services.manager.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerProductController {
        private final ProductService productService;

        @PostMapping("/product")
        public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO dto) throws Exception {
            ProductDTO dto1 = productService.addProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto1);
        }

        @GetMapping("/products")
        public ResponseEntity<List<ProductDTO>> getAllProducts() {
            List<ProductDTO> dtos = productService.getAllProducts();
            return ResponseEntity.ok(dtos);
        }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@PathVariable String name) {
        List<ProductDTO> dtos = productService.getAllProductsByName(name);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
