package com.brandolff.msproductmanagement.entity;

import com.brandolff.msproductmanagement.enums.SizeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false )
    private Integer stock;

    @Column( nullable = false )
    private SizeEnum size;

    @ManyToOne
    private ProductEntity product;

}
