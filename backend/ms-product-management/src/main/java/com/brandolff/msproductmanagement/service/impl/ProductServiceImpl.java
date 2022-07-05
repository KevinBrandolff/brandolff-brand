package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.entity.CategoryEntity;
import com.brandolff.msproductmanagement.entity.ProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import com.brandolff.msproductmanagement.exception.generic.ResourceNotFoundException;
import com.brandolff.msproductmanagement.repository.ProductRepository;
import com.brandolff.msproductmanagement.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl( ProductRepository repository ) {
        this.repository = repository;
    }

    @Override
    public ProductDTO createProduct( ProductDTO productDTO ) {
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties( productDTO, productEntity );
        return new ProductDTO( repository.save(productEntity) );
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        AtomicReference<ProductEntity> producttEntityUpdated = new AtomicReference<>(ProductEntity.builder().build());

        repository.findById( productDTO.getId() ).ifPresentOrElse(
                (productEntity) -> { BeanUtils.copyProperties( productDTO, productEntity );
                    producttEntityUpdated.set(repository.save(productEntity));
                },
                () -> {
                    throw new ResourceNotFoundException();
                }

        );

        return new ProductDTO(producttEntityUpdated.get());
    }

    @Override
    public void deleteProductById(Integer id) {
        repository.deleteById( id );
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllBySize(String size) {
        return repository.findAllBySize( SizeEnum.valueOf(size) ).stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCategories(List<CategoryDTO> categoriesDTO) {
        return repository.findAllByCategoriesIn(
                categoriesDTO.stream().map(
                      (categoryDTO) -> {
                        CategoryEntity categoryEntity = new CategoryEntity();
                        BeanUtils.copyProperties( categoryDTO, categoryEntity );
                        return categoryEntity;
                    } ).collect(Collectors.toList())
                 ).stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Integer id){
        return new ProductDTO( repository.findById(id).orElseThrow( () -> new RuntimeException() ) );
    }

    @Override
    public ProductDTO findByName(String name) {
        return new ProductDTO( repository.findByName(name).orElseThrow( () -> new RuntimeException() ) );
    }
}
