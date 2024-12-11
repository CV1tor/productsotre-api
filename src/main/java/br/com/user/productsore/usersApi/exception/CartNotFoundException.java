package br.com.user.productsore.usersApi.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("There is no cart available for this user.");
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
