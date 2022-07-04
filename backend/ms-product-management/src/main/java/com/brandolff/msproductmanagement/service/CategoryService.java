package com.brandolff.msproductmanagement.service;

import com.brandolff.msproductmanagement.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory( CategoryDTO categoryDTO );
    CategoryDTO updateCategory( CategoryDTO categoryDTO );
    void deleteCategoryById( Integer id );
    List<CategoryDTO> findAll();

}
