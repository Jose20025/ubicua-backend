package com.joseca.blogapi.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;

    private String fieldName;

    private String fieldValue;

    // Constructor
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }
}