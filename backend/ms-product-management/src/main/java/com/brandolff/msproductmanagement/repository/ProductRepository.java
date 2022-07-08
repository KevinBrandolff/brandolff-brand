package com.brandolff.msproductmanagement.repository;

import com.brandolff.msproductmanagement.entity.CategoryEntity;
import com.brandolff.msproductmanagement.entity.ProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    List<ProductEntity> findAll();
    List<ProductEntity> findAllByInventorySizeAndInventoryStockGreaterThan( SizeEnum size, Integer zero );
    ProductEntity findById( int id );
    Optional<ProductEntity> findByName(String name );
    List<ProductEntity> findAllByCategoriesIn( List<CategoryEntity> categories);

}
