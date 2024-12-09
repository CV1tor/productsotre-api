package br.com.user.productsore.usersApi.dto;

import br.com.user.productsore.usersApi.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull(message = "Username can't be null")
        @NotBlank(message = "Username can't be blank")
        String username,

        @NotNull(message = "Password can't be null")
        @NotBlank(message = "Password can't be blank")
        String password,

        @NotNull(message = "User must have a role")
        UserRole role
) {

}
