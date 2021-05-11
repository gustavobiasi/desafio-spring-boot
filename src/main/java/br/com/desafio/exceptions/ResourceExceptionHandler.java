package br.com.desafio.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        if (ex.getMessage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}