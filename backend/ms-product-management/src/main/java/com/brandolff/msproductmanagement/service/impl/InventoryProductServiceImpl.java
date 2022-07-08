package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.InventoryProductDTO;
import com.brandolff.msproductmanagement.entity.InventoryProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import com.brandolff.msproductmanagement.exception.generic.ResourceNotFoundException;
import com.brandolff.msproductmanagement.repository.InventoryProductRepository;
import com.brandolff.msproductmanagement.service.InventoryProductService;
import org.springframework.stereotype.Service;

@Service
public class InventoryProductServiceImpl implements InventoryProductService {

    private final InventoryProductRepository repository;

    public InventoryProductServiceImpl( InventoryProductRepository repository ) {
        this.repository = repository;
    }

    @Override
    public InventoryProductDTO addStock( Integer amount, Integer productId, String size ) {
        InventoryProductEntity inventoryProductEntity = repository.findByProductIdAndSize( productId, SizeEnum.valueOf(size) );
        inventoryProductEntity.setStock( inventoryProductEntity.getStock() + amount );
        return new InventoryProductDTO( repository.save( inventoryProductEntity ) );
    }

    @Override
    public InventoryProductDTO removeStock( Integer amount, Integer productId, String size ) {
        InventoryProductEntity inventoryProductEntity = repository.findByProductIdAndSize( productId, SizeEnum.valueOf(size) );
        inventoryProductEntity.setStock( inventoryProductEntity.getStock() - amount );
        return new InventoryProductDTO( repository.save( inventoryProductEntity ) );
    }

    @Override
    public InventoryProductDTO updateStock( Integer amount, Integer productId, String size ) {
        InventoryProductEntity inventoryProductEntity = repository.findByProductIdAndSize( productId, SizeEnum.valueOf(size) );
        inventoryProductEntity.setStock( amount );
        return new InventoryProductDTO( repository.save( inventoryProductEntity ) );
    }

    @Override
    public InventoryProductDTO getStock( Integer productId, String size ) {
        InventoryProductEntity inventoryProductEntity = repository.findByProductIdAndSize( productId, SizeEnum.valueOf(size) );
        if ( inventoryProductEntity != null ) {
            return new InventoryProductDTO( inventoryProductEntity );
        }else {
            throw new ResourceNotFoundException();
        }
    }

}
