package com.revature.ecommerce.exceptions;

public abstract class NetworkException extends RuntimeException {
    public NetworkException(String message) {
        super(message);
    }

    public abstract int getStatusCode();
}