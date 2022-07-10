package com.tecnotree.exception;

/**
 * The class RestResponse use InternalExceptionHandler.
 */
public class RestResponse {

    private RestResponseType code;
    private String message;

    public RestResponse(RestResponseType code, String message) {
        this.code = code;
        this.message = message;
    }
}
