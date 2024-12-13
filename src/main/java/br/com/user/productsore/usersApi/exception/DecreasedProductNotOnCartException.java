package br.com.user.productsore.usersApi.exception;

public class DecreasedProductNotOnCartException extends RuntimeException {
    public DecreasedProductNotOnCartException() {
        super("The product that you want to decrease quantity was removed from the cart.");
    }

    public DecreasedProductNotOnCartException(String message) {
        super(message);
    }
}
