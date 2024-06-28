package com.anhntv.ecom.services.manager.product;

import com.anhntv.ecom.dto.ProductDTO;
import com.anhntv.ecom.entities.Category;
import com.anhntv.ecom.entities.Product;
import com.anhntv.ecom.repository.CategoryRepository;
import com.anhntv.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDTO addProduct(ProductDTO dto) throws IOException {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImg(dto.getImg().getBytes());

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()
                -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product).getDTO();
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProductsByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
