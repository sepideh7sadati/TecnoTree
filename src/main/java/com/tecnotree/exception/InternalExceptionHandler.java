package com.tecnotree.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class InternalExceptionHandler create all Exception.
 */
@ControllerAdvice
@Slf4j
public class InternalExceptionHandler {

   @ExceptionHandler({PostException.class})
    public ResponseEntity<RestResponse> handlePostException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("PostException take place, [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new RestResponse(RestResponseType.ERROR, ex.getMessage()));
    }

    @ExceptionHandler({CommentException.class})
    public ResponseEntity<RestResponse> handleCommentException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        //  log.error("PostException take place, [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new RestResponse(RestResponseType.ERROR, ex.getMessage()));
    }
}
