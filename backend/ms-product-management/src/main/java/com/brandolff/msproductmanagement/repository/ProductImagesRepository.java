package com.brandolff.msproductmanagement.repository;

import com.brandolff.msproductmanagement.entity.ProductImagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepository extends CrudRepository<ProductImagesEntity, Integer> {
}
