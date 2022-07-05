package com.brandolff.msproductmanagement.exception.category;

public class CategoryPersistException extends RuntimeException{
    public CategoryPersistException( String msg ) {
        super("Error to persist category: " + msg);
    }
}
