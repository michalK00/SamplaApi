package com.sampla.samplaapi.rest;

import com.sampla.samplaapi.rest.exceptions.ResearchDeletionException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;


@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    List<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
    }

    @ExceptionHandler(ResearchDeletionException.class)
    ResponseEntity<?> handleResearchDeletionException(ResearchDeletionException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    ResponseEntity<?> handleOtherExceptions(Exception ex) {
//        return ResponseEntity.internalServerError().build();
//    }
}
