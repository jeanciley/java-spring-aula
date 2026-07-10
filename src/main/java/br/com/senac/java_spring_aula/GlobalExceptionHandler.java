package br.com.senac.java_spring_aula;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        String messagem = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(Map.of("erro", messagem));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleValidation(IllegalArgumentException ex) {

        String messagem = ex.getMessage();

        return ResponseEntity
                .badRequest()
                .body(Map.of("erro", messagem));
    }
}
