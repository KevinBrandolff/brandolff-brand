package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.service.ProductService;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/product" )
public class ProductController {

    private final ProductService service;

    public ProductController( ProductService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct( @RequestBody @Valid ProductDTO productDTO ){
        return new ResponseEntity<>( service.createProduct( productDTO ), CREATED );
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct( @RequestBody @Valid ProductDTO productDTO ){
        return ResponseEntity.ok( service.updateProduct(productDTO) );
    }

    @DeleteMapping( value = "/{id}" )
    @ResponseStatus( NO_CONTENT )
    public void deleteProduct( @PathVariable Integer id ){
        service.deleteProductById(id);
    }

    @GetMapping( value = "/id/{id}" )
    public ResponseEntity<ProductDTO> findById( @PathVariable Integer id ){
        return ResponseEntity.ok( service.findById(id) );
    }

    @GetMapping( value = "/name/{name}" )
    public ResponseEntity<ProductDTO> findByName( @PathVariable String name ){
        return ResponseEntity.ok( service.findByName(name) );
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok( service.findAll() );
    }

    @GetMapping( value = "/size/{size}" )
    public ResponseEntity<List<ProductDTO>> findAllBySize( @PathVariable String size ){
        return ResponseEntity.ok( service.findAllBySizeAndStockGreaterThanZero(size) );
    }

    @GetMapping( value = "/by-categories" )
    public ResponseEntity<List<ProductDTO>> findAllByCategories(@RequestBody List<CategoryDTO> categories){
        return ResponseEntity.ok( service.findAllByCategories( categories ) );
    }
}
