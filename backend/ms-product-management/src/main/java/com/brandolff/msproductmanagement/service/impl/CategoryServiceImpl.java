package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.entity.CategoryEntity;
import com.brandolff.msproductmanagement.exception.category.CategoryPersistException;
import com.brandolff.msproductmanagement.exception.generic.ResourceNotFoundException;
import com.brandolff.msproductmanagement.exception.product.ProductPersistException;
import com.brandolff.msproductmanagement.repository.CategoryRepository;
import com.brandolff.msproductmanagement.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl( CategoryRepository repository ) {
        this.repository = repository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        verifyCategory( categoryDTO.getCategory() );
        CategoryEntity categoryEntity = CategoryEntity.builder().build();
        BeanUtils.copyProperties( categoryDTO, categoryEntity );
        return new CategoryDTO( repository.save( categoryEntity ) );
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {

        AtomicReference<CategoryEntity> categoryEntityUpdated = new AtomicReference<>(CategoryEntity.builder().build());

        repository.findById( categoryDTO.getId() ).ifPresentOrElse(
                (categoryEntity) -> {
                    if ( !categoryDTO.getCategory().equals( categoryEntity.getCategory() ) ) {
                        verifyCategory( categoryDTO.getCategory() );
                    }
                    BeanUtils.copyProperties( categoryDTO, categoryEntity );
                    categoryEntityUpdated.set(repository.save(categoryEntity));
                    },
                () -> {
                    throw new ResourceNotFoundException();
                }

        );

        return new CategoryDTO(categoryEntityUpdated.get());
    }

    @Override
    public void deleteCategoryById(Integer id) {
        repository.deleteCategoryAndCategoryRelations(id);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return convertListEntityToDTO( repository.findAll() );
    }

    private List<CategoryDTO> convertListEntityToDTO( List<CategoryEntity> listEntity ) {
        return listEntity.stream().map( categoryEntity -> new CategoryDTO(categoryEntity) ).collect(Collectors.toList());
    }

    private void verifyCategory( String category ) {
        repository.findByCategory( category ).ifPresent( productEntity -> {throw new CategoryPersistException( "Category name already used!" );} );
    }
}
