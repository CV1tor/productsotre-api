package br.com.user.productsore.usersApi.dto;

import jakarta.validation.constraints.NotNull;

public record WalletViewDTO (@NotNull UserDTO user, @NotNull Integer balance){
}
