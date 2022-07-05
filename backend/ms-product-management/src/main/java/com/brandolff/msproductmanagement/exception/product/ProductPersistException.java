package com.brandolff.msproductmanagement.exception.product;

public class ProductPersistException extends RuntimeException{
    public ProductPersistException( String msg ) {
        super("Error to persist Product: " + msg);
    }
}
