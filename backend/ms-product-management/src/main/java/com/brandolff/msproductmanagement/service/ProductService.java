package com.brandolff.msproductmanagement.service;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO createProduct( ProductDTO productDTO );
    ProductDTO updateProduct( ProductDTO productDTO );
    void deleteProductById( Integer id );
    List<ProductDTO> findAll();
    List<ProductDTO> findAllBySizeAndStockGreaterThanZero( String size );
    List<ProductDTO> findAllByCategories( List<CategoryDTO> categoriesDTO );
    ProductDTO findById( Integer id );
    ProductDTO findByName(String name );

}
