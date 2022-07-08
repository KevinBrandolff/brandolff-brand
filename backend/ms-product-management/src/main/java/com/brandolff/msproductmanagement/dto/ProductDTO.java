package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.ProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private Integer id;
    @NotNull( message = "name required" )
    private String name;
    @NotNull( message = "price required" )
    private Double price;
    @NotNull( message = "description required" )
    private String description;
    @NotNull( message = "category required" )
    private List<CategoryDTO> categories;
    private List<InventoryProductDTO> inventory;
    private List<ProductImagesDTO> imagesUrls;

    public ProductDTO( ProductEntity productEntity ) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
        this.inventory = productEntity.getInventory() != null ? productEntity.getInventory().stream()
                .map( (inventory) -> new InventoryProductDTO(inventory) )
                .collect(Collectors.toList()) : null;
        this.categories = productEntity.getCategories() != null ? productEntity.getCategories().stream()
                .map( (category) -> new CategoryDTO(category) )
                .collect(Collectors.toList()) : null;
        this.imagesUrls = productEntity.getImagesUrls() != null ? productEntity.getImagesUrls().stream()
                .map( (image) -> new ProductImagesDTO(image) )
                .collect(Collectors.toList()) : null;
    }

}
