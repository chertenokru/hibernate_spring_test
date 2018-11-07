package ru.chertenok.spring.hibernate.controllers.API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.chertenok.spring.hibernate.util.ErrorAPI;
import ru.chertenok.spring.hibernate.util.ResourceErrorResponse;
import ru.chertenok.spring.hibernate.util.ResourceNotFoundErrorAPI;

@RestControllerAdvice
public class ErrorRestHandler {

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(ErrorAPI exc) {
        ResourceErrorResponse errorAPIResponse = new ResourceErrorResponse();
        errorAPIResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorAPIResponse.setMessage(exc.getMessage());
        errorAPIResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ResourceErrorResponse>(errorAPIResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundErrorAPI exc) {
        ResourceErrorResponse errorAPIResponse = new ResourceErrorResponse();
        errorAPIResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorAPIResponse.setMessage(exc.getMessage());
        errorAPIResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ResourceErrorResponse>(errorAPIResponse, HttpStatus.BAD_REQUEST);
    }

}
