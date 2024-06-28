package com.anhntv.ecom.services.customer;

import com.anhntv.ecom.dto.ProductDTO;

import java.util.List;

public interface CustomerProductService {

    List<ProductDTO> searchProductByTitle(String title);

    List<ProductDTO> getAllProducts();
}
