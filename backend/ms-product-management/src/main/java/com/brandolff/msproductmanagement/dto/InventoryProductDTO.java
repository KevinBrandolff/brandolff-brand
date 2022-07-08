package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.InventoryProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryProductDTO {

    private Integer id;
    private Integer stock;
    private SizeEnum size;

    public InventoryProductDTO( InventoryProductEntity inventoryProductEntity ) {
        this.id = inventoryProductEntity.getId();
        this.stock = inventoryProductEntity.getStock();
        this.size = inventoryProductEntity.getSize();
    }

}
