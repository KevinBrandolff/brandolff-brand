package com.brandolff.msproductmanagement.exception.generic;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found!");
    }

    public ResourceNotFoundException( String msg ) {
        super( "Resource not found! " + msg );
    }
}
