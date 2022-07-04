package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private Integer id;
    private String name;
    private Double value;
    private String description;
    private SizeEnum size;
    private List<CategoryDTO> categories;
    private List<ProductImagesDTO> imagesUrls;

}
