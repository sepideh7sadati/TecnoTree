package com.tecnotree.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum RestResponseType use InternalExceptionHandler.
 */
public enum RestResponseType {

    ERROR("error");
    private String name;

    RestResponseType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
