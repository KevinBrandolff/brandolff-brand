package com.brandolff.msproductmanagement.dto;

import com.brandolff.msproductmanagement.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Integer id;
    @NotNull( message = "category required" )
    private String category;

    public CategoryDTO(CategoryEntity categoryEntity ) {
        this.id = categoryEntity.getId();
        this.category = categoryEntity.getCategory();
    }

}
