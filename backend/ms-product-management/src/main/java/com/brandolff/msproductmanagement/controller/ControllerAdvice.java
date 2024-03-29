package com.brandolff.msproductmanagement.controller;

import com.brandolff.msproductmanagement.exception.category.CategoryPersistException;
import com.brandolff.msproductmanagement.exception.generic.ResourceNotFoundException;
import com.brandolff.msproductmanagement.exception.product.ProductPersistException;
import com.brandolff.msproductmanagement.exception.productImages.ProductImagesPersistException;
import com.brandolff.msproductmanagement.util.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(toList());
        return new ApiErrors(errors);
    }

    @ExceptionHandler( ResponseStatusException.class )
    @ResponseStatus( NOT_FOUND )
    public ApiErrors responseStatusException( ResponseStatusException ex ){
        return new ApiErrors(ex.getReason());
    }

    @ExceptionHandler( Exception.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors exception( Exception ex ){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler( ResourceNotFoundException.class )
    @ResponseStatus( NOT_FOUND )
    public ApiErrors ResourceNotFoundException( ResourceNotFoundException ex ){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler( ProductPersistException.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors productPersistException( ProductPersistException ex ){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler( CategoryPersistException.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors categoryPersistException( CategoryPersistException ex ){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler( ProductImagesPersistException.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors productImagesPersistException( ProductImagesPersistException ex ){
        return new ApiErrors(ex.getMessage());
    }

}
