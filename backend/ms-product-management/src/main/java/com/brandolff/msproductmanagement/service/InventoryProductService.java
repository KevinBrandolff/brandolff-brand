package com.brandolff.msproductmanagement.service;

import com.brandolff.msproductmanagement.dto.InventoryProductDTO;

public interface InventoryProductService {

    InventoryProductDTO addStock( Integer amount, Integer productId, String size );

    InventoryProductDTO removeStock( Integer amount, Integer productId, String size );

    InventoryProductDTO updateStock( Integer amount, Integer productId, String size );

    InventoryProductDTO getStock( Integer productId, String size );

}
