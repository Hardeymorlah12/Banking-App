package com.hardeymorlah.Banking_App.exception;

public class TransactionException extends RuntimeException {
    private String message;

    public TransactionException(String message){
        super(message);
    }
}
