package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.InventoryProductDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.entity.CategoryEntity;
import com.brandolff.msproductmanagement.entity.InventoryProductEntity;
import com.brandolff.msproductmanagement.entity.ProductEntity;
import com.brandolff.msproductmanagement.enums.SizeEnum;
import com.brandolff.msproductmanagement.exception.generic.ResourceNotFoundException;
import com.brandolff.msproductmanagement.exception.product.ProductPersistException;
import com.brandolff.msproductmanagement.repository.CategoryRepository;
import com.brandolff.msproductmanagement.repository.ProductRepository;
import com.brandolff.msproductmanagement.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl( ProductRepository repository, CategoryRepository categoryRepository ) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO createProduct( ProductDTO productDTO ) {
        verifyProductName( productDTO.getName() );
        verifyCategories( productDTO.getCategories() );
        verifyInventoryAndCreateInventoriesRemaining( productDTO.getInventory() );
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties( productDTO, productEntity );
        productEntity.setCategories( productDTO.getCategories().stream().map( (categoryDTO) -> {
            CategoryEntity categoryEntity = CategoryEntity.builder().build();
            BeanUtils.copyProperties( categoryDTO, categoryEntity );
            return categoryEntity;
        } ).collect(Collectors.toList()) );
        productEntity.setInventory( productDTO.getInventory().stream().map( (inventoryDTO) -> {
            InventoryProductEntity inventoryEntity = InventoryProductEntity.builder().build();
            BeanUtils.copyProperties( inventoryDTO, inventoryEntity );
            inventoryEntity.setProduct( productEntity );
            return inventoryEntity;
        } ).collect(Collectors.toList()) );
        return new ProductDTO( repository.save(productEntity) );
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        AtomicReference<ProductEntity> productEntityUpdated = new AtomicReference<>(ProductEntity.builder().build());

        repository.findById( productDTO.getId() ).ifPresentOrElse(
                (productEntity) -> {
                    if ( !productDTO.getName().equals( productEntity.getName() ) ) {
                        verifyProductName( productDTO.getName() );
                    }
                    verifyCategories( productDTO.getCategories() );
                    BeanUtils.copyProperties( productDTO, productEntity );
                    productEntity.setCategories( productDTO.getCategories().stream().map( (categoryDTO) -> {
                        CategoryEntity categoryEntity = CategoryEntity.builder().build();
                        BeanUtils.copyProperties( categoryDTO, categoryEntity );
                        return categoryEntity;
                    } ).collect(Collectors.toList()) );
                    productEntityUpdated.set(repository.save(productEntity));
                },
                () -> {
                    throw new ResourceNotFoundException();
                }

        );

        return new ProductDTO(productEntityUpdated.get());
    }

    @Override
    public void deleteProductById(Integer id) {
        repository.deleteById( id );
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllBySizeAndStockGreaterThanZero(String size) {
        return repository.findAllByInventorySizeAndInventoryStockGreaterThan( SizeEnum.valueOf(size), 0 ).stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCategories(List<CategoryDTO> categoriesDTO) {
        return repository.findAllByCategoriesIn(
                categoriesDTO.stream().map(
                      (categoryDTO) -> {
                        CategoryEntity categoryEntity = new CategoryEntity();
                        BeanUtils.copyProperties( categoryDTO, categoryEntity );
                        return categoryEntity;
                    } ).collect(Collectors.toList())
                 ).stream().map( ProductDTO::new ).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Integer id){
        return new ProductDTO( repository.findById(id).orElseThrow(ResourceNotFoundException::new) );
    }

    @Override
    public ProductDTO findByName(String name) {
        return new ProductDTO( repository.findByName(name).orElseThrow(ResourceNotFoundException::new) );
    }

    private void verifyProductName( String name ) {
        repository.findByName( name ).ifPresent( productEntity -> {throw new ProductPersistException( "Name already used!" );} );
    }

    private void verifyCategories( List<CategoryDTO> listCategoryDTO ) {
        if ( listCategoryDTO != null ) {
            listCategoryDTO.forEach(categoryDTO -> {
                CategoryEntity categoryEntity = CategoryEntity.builder().build();
                BeanUtils.copyProperties(categoryDTO, categoryEntity);
                categoryRepository.findByIdAndCategory(categoryEntity.getId(), categoryEntity.getCategory()).ifPresentOrElse(categoryEntity1 -> {
                        },
                        () -> {
                            throw new ProductPersistException("Wrong categories informations!");
                        });
            });
        }
    }

    private void verifyInventoryAndCreateInventoriesRemaining(List<InventoryProductDTO> inventoryProductDTOList ) {

        List<Integer> inventoriesExisting = new ArrayList<>();

        for ( InventoryProductDTO inventoryDTO : inventoryProductDTOList ) {
            int index = 0;
            for ( InventoryProductDTO inventoryDTOToCompare : inventoryProductDTOList ) {
                if ( inventoryDTO.getSize().equals( inventoryDTOToCompare.getSize() ) ) {
                    index++;
                    inventoriesExisting.add( inventoryDTO.getSize().ordinal() );
                }
            }
            if ( index > 1 ) {
                throw new ProductPersistException( "Invalid inventory information!" );
            }
        }

        for( SizeEnum size : SizeEnum.values() ) {
            if ( !inventoriesExisting.contains( size.ordinal() ) ) {
                inventoryProductDTOList.add( new InventoryProductDTO( size, 0 ) );
            }
        }
    }
}
