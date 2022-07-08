package com.brandolff.msproductmanagement.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

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
    private Double price;

    @Column( nullable = false )
    private String description;

    @OneToMany( mappedBy = "product")
    @Cascade( {PERSIST, MERGE, REFRESH, REMOVE} )
    private List<InventoryProductEntity> inventory;

    @ManyToMany( cascade = CascadeType.MERGE )
    @JoinTable( name = "product_entity_category",
            joinColumns = @JoinColumn( name = "product_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" ) )
    private List<CategoryEntity> categories;

    @OneToMany( mappedBy = "product" )
    private List<ProductImagesEntity> imagesUrls;

}
