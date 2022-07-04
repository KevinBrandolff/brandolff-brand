package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Integer id;
    private String category;

    public CategoryDTO(CategoryEntity categoryEntity ) {
        this.id = categoryEntity.getId();
        this.category = categoryEntity.getCategory();
    }

}
