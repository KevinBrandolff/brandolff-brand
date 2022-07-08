package com.brandolff.msproductmanagement.repository;

import com.brandolff.msproductmanagement.entity.InventoryProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductRepository extends CrudRepository< InventoryProductEntity, Integer > {

    InventoryProductEntity findByProductIdAndSize(Integer id, SizeEnum size);

}
