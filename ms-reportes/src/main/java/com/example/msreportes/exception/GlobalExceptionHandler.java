package com.example.msreportes.exception;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(ResourceNotFoundException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Datos de entrada invalidos", request.getRequestURI(), errores);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> manejarEstadoInvalido(IllegalStateException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> manejarErrorFeign(FeignException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.SERVICE_UNAVAILABLE, "Error al comunicarse con otro microservicio", request.getRequestURI(), null);
    }

    private ResponseEntity<ErrorResponse> construirRespuesta(HttpStatus status, String message, String path, Map<String, String> details) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), status.getReasonPhrase(), message, path, details));
    }
}
