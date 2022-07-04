package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.repository.ProductRepository;
import com.brandolff.msproductmanagement.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl( ProductRepository repository ) {
        this.repository = repository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) {

    }

    @Override
    public List<ProductDTO> findAll() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllBySize(String size) {
        return null;
    }

    @Override
    public List<ProductDTO> findAllByCategories(List<CategoryDTO> categoriesDTO) {
        return null;
    }

    @Override
    public ProductDTO findById(Integer id) {
        return null;
    }

    @Override
    public ProductDTO findByName(String name) {
        return null;
    }
}
