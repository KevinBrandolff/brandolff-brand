package com.brandolff.msproductmanagement.exception.productImages;

public class ProductImagesPersistException extends RuntimeException{
    public ProductImagesPersistException( String msg ) {
        super("Error to persist productImages: " + msg);
    }
}
