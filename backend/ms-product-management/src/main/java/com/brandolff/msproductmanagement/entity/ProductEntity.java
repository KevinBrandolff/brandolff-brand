package com.brandolff.msproductmanagement.entity;

import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( unique = true, nullable = false )
    private String name;

    @Column( nullable = false )
    private Double value;

    @Column( unique = true, nullable = false )
    private String description;

    @Column( nullable = false )
    private SizeEnum size;

    @ManyToMany
    private List<CategoryEntity> categories;

    @OneToMany( mappedBy = "product" )
    private List<ProductImagesEntity> imagesUrls;

}
