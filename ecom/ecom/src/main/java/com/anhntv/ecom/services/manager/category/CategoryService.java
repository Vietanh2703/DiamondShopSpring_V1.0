package com.anhntv.ecom.services.manager.category;

import com.anhntv.ecom.dto.CategoryDTO;
import com.anhntv.ecom.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO dto);

    List<Category> getAllCategories();


}
