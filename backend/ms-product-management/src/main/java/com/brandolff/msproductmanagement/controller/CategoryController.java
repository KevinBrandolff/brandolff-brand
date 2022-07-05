package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.dto.CategoryDTO;
import com.brandolff.msproductmanagement.service.CategoryService;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController( CategoryService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory( @RequestBody @Valid CategoryDTO categoryDTO ) {
        return new ResponseEntity<>( service.createCategory( categoryDTO ), CREATED );
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory( @RequestBody @Valid CategoryDTO categoryDTO ) {
        return ResponseEntity.ok( service.updateCategory( categoryDTO ) );
    }

    @DeleteMapping( value = "/{id}" )
    @ResponseStatus( NO_CONTENT )
    public void deleteById( @PathVariable Integer id ) {
        service.deleteCategoryById( id );
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok( service.findAll() );
    }

}
