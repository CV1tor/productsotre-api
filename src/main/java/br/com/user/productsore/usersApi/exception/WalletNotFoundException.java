package br.com.user.productsore.usersApi.exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException() {
        super("The user don't have a wallet. Please contact suport.");
    }

    public WalletNotFoundException(String message) {
        super(message);
    }
}
