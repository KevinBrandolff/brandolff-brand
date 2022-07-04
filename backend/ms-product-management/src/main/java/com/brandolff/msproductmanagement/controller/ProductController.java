package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.service.ProductService;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping( value = "/product" )
public class ProductController {

    private final ProductService service;

    public ProductController( ProductService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct( @RequestParam("file") MultipartFile file ){
        return null;
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(){
        return null;
    }

    @DeleteMapping
    @ResponseStatus( NO_CONTENT )
    public void deleteProduct(){
    }

    @GetMapping( value = "/id/{id}" )
    public ResponseEntity<ProductDTO> findById(){
        return null;
    }

    @GetMapping( value = "/name/{name}" )
    public ResponseEntity<ProductDTO> findByName(){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        return null;
    }

    @GetMapping( value = "/size/{size}")
    public ResponseEntity<List<ProductDTO>> findAllBySize(){
        return null;
    }

    @GetMapping( value = "/by-categories" )
    public ResponseEntity<List<ProductDTO>> findAllByCategories(){
        return null;
    }
}
