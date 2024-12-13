package br.com.user.productsore.usersApi.exception;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException() {
        super("You do not have enough balance to complete the transaction");
    }

    public BalanceNotEnoughException(String message) {
        super(message);
    }
}
