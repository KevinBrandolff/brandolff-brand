package com.brandolff.msproductmanagement.repository;

import com.brandolff.msproductmanagement.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository< CategoryEntity, Integer > {

    List<CategoryEntity> findAll();
    CategoryEntity findByCategory( String category );

    @Modifying
    @Query( value = "DELETE a.*, b.* \n" +
            "FROM product_entity_category a \n" +
            "LEFT JOIN category_entity b \n" +
            "ON b.id = a.category_id \n" +
            "WHERE a.category_id = :id", nativeQuery = true )
    void deleteCategoryAndCategoryRelations(Integer id);

}
