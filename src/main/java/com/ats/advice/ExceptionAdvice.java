package com.ats.advice;


import com.ats.exception.BadRequestException;
import com.ats.message.ErrorMessage;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {
    ErrorMessage errorMessage;

    @Autowired
    public ExceptionAdvice(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException exp){
        errorMessage.setMessage(exp.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadClientRequest(BadRequestException exp){
        errorMessage.setMessage(exp.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleHibernateException(RuntimeException exp){
        errorMessage.setMessage(exp.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorMessage);
    }
}
