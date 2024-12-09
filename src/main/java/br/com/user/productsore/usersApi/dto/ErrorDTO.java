package br.com.user.productsore.usersApi.dto;

import org.springframework.http.HttpStatus;

public record ErrorDTO(HttpStatus status, String message) {
}
