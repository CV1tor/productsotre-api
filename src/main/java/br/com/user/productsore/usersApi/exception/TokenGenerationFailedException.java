package br.com.user.productsore.usersApi.exception;

public class TokenGenerationFailedException extends RuntimeException {
    public TokenGenerationFailedException() {
        super("Token generation failed");
    }

    public TokenGenerationFailedException(String message) {
        super(message);
    }

}
