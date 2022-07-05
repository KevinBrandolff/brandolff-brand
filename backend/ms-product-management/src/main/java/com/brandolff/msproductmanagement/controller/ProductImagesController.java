package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.dto.ProductDTO;
import com.brandolff.msproductmanagement.dto.ProductImagesDTO;
import com.brandolff.msproductmanagement.service.ProductImagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping( value = "/product-images" )
public class ProductImagesController {

    private final ProductImagesService service;

    public ProductImagesController( ProductImagesService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductImagesDTO> saveImages(@RequestParam("file") MultipartFile file, @RequestParam Integer id ){
        return new ResponseEntity<>( service.saveImages( file, id), CREATED );
    }

}
