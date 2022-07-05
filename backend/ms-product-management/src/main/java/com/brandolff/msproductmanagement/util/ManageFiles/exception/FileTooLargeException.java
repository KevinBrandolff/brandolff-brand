package com.brandolff.msproductmanagement.util.ManageFiles.exception;

public class FileTooLargeException extends RuntimeException{
    public FileTooLargeException(String msg){
        super(msg);
    }
}
