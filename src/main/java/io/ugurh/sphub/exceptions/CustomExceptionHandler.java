package io.ugurh.sphub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 18.04.2023 - 19:34
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> processUnmergeException(final MethodArgumentNotValidException ex) {

        List<String> validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map((error -> error.getDefaultMessage() == null ? "" : error.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
