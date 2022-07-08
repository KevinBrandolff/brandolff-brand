package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.dto.InventoryProductDTO;
import com.brandolff.msproductmanagement.service.InventoryProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/inventory" )
public class InventoryProductController {

    private final InventoryProductService service;

    public InventoryProductController( InventoryProductService service ) {
        this.service = service;
    }

    @PostMapping( "/add/{productId}/amount/{amount}/size/{size}" )
    public ResponseEntity<InventoryProductDTO> addStock( @PathVariable Integer productId, @PathVariable Integer amount, @PathVariable String size ) {
        return ResponseEntity.ok( service.addStock( amount, productId, size ) );
    }

    @PostMapping( "/remove/{productId}/amount/{amount}/size/{size}" )
    public ResponseEntity<InventoryProductDTO> removeStock( @PathVariable Integer productId, @PathVariable Integer amount, @PathVariable String size ) {
        return ResponseEntity.ok( service.removeStock( amount, productId, size ) );
    }

    @PostMapping( "/update/{productId}/amount/{amount}/size/{size}" )
    public ResponseEntity<InventoryProductDTO> updateStock( @PathVariable Integer productId, @PathVariable Integer amount, @PathVariable String size ) {
        return ResponseEntity.ok( service.updateStock( amount, productId, size ) );
    }

    @GetMapping( "/get-stock/{productId}/size/{size}" )
    public ResponseEntity<InventoryProductDTO> getStock( @PathVariable Integer productId, @PathVariable String size ) {
        return ResponseEntity.ok( service.getStock( productId, size ) );
    }

}
