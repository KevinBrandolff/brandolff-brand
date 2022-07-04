package com.brandolff.msproductmanagement.service;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct( ProductDTO productDTO );
    ProductDTO updateProduct( ProductDTO productDTO );
    void deleteProductById( Integer id );
    List<ProductDTO> findAll();
    List<ProductDTO> findAllBySize( String size );
    List<ProductDTO> findAllByCategories( List<CategoryDTO> categoriesDTO );
    ProductDTO findById( Integer id );
    ProductDTO findByName( String name );

}
