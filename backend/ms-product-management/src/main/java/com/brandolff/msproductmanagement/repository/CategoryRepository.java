package com.brandolff.msproductmanagement.repository;

import com.brandolff.msproductmanagement.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository< CategoryEntity, Integer > {

    List<CategoryEntity> findAll();
    CategoryEntity findByCategory( String category );

}
