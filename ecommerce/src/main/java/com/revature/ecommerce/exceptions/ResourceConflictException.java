package com.revature.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends NetworkException{
    public ResourceConflictException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode(){
        return 409;
    }
}