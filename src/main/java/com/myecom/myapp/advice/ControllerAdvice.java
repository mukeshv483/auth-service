package com.myecom.myapp.advice;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

   ApiError apiError =
        ApiError.builder()
            .path(((ServletWebRequest) request).getRequest().getRequestURI())
            .error(HttpStatus.BAD_REQUEST)
            .status(HttpStatus.BAD_REQUEST.value())
            .timestamp(Instant.now().toEpochMilli())
            .build();
    apiError.setMessage(errors.stream().collect(Collectors.joining("; ")));

    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getError(), request);
  }
}
