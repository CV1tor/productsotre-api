package br.com.user.productsore.usersApi.conf;

import br.com.user.productsore.usersApi.dto.ErrorDTO;
import br.com.user.productsore.usersApi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorDTO> userNotFoundHandler(UserNotFoundException exception) {
        ErrorDTO errorResponse = new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ErrorDTO> productNotFoundHandler(ProductNotFoundException exception) {
        ErrorDTO errorResponse = new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenGenerationFailedException.class)
    private ResponseEntity<ErrorDTO> tokenGenerationFailHandler(TokenGenerationFailedException exception) {
        ErrorDTO errorResponse = new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductOutOfStockException.class)
    private ResponseEntity<ErrorDTO> productOutOfStockHandler(ProductOutOfStockException exception) {
        ErrorDTO errorResponse = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CartNotFoundException.class)
    private ResponseEntity<ErrorDTO> cartNotFoundHandler(CartNotFoundException exception) {
        ErrorDTO errorResponse = new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
