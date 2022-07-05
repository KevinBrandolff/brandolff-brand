package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.ProductImagesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductImagesDTO {

    private Integer id;
    private String imageUrl;

    public ProductImagesDTO( ProductImagesEntity productImagesEntity ) {
        this.id = productImagesEntity.getId();
        this.imageUrl = productImagesEntity.getImageUrl();
    }

    public ProductImagesDTO( String url ) {
        this.imageUrl = url;
    }

}
