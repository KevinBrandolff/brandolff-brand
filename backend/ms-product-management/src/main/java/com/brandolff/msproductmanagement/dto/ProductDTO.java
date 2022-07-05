package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.ProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private SizeEnum size;
    private List<CategoryDTO> categories;
    private List<ProductImagesDTO> imagesUrls;

    public ProductDTO( ProductEntity productEntity ) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
        this.size = productEntity.getSize();
        this.categories = productEntity.getCategories() != null ? productEntity.getCategories().stream()
                .map( (category) -> new CategoryDTO(category) )
                .collect(Collectors.toList()) : null;
        this.imagesUrls = productEntity.getImagesUrls() != null ? productEntity.getImagesUrls().stream()
                .map( (image) -> new ProductImagesDTO(image) )
                .collect(Collectors.toList()) : null;
    }

}
