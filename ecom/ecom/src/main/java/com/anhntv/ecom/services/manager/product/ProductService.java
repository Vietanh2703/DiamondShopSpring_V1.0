package com.anhntv.ecom.services.manager.product;

import com.anhntv.ecom.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO dto) throws Exception;

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsByName(String name);

    boolean deleteProduct(Long id);
}
