package com.myecom.myapp.advice;


import com.myecom.myapp.exception.UserAlreadyExistException;
import com.myecom.myapp.metrices.MetricesCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
   @Autowired
    private MetricesCollector metricesCollector;
    @ExceptionHandler(UserAlreadyExistException.class)
    public ProblemDetail handleExceptionStatus(Exception ex,
                                               WebRequest request) {
        metricesCollector.incrementExceptionCount();
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.BAD_REQUEST,ex.getLocalizedMessage());
        problemDetails.setTitle(ex.getMessage());
        return problemDetails;
    }
    @ExceptionHandler(Throwable.class)
    public ProblemDetail handleExceptionStatus5xx(Exception ex,
                                               WebRequest request) {
        metricesCollector.incrementExceptionCount();
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.INTERNAL_SERVER_ERROR,ex.getLocalizedMessage());
        problemDetails.setTitle(ex.getMessage());
        return problemDetails;
    }
}
