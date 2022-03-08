package com.example.spring_k8s_crud.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExpetion extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExpetion(String message) {
        super(message);
    }
}
