package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.domain.wallet.Wallet;
import br.com.user.productsore.usersApi.dto.UserDTO;
import br.com.user.productsore.usersApi.dto.WalletDTO;
import br.com.user.productsore.usersApi.dto.WalletViewDTO;
import br.com.user.productsore.usersApi.exception.BalanceNotEnoughException;
import br.com.user.productsore.usersApi.exception.WalletNotFoundException;
import br.com.user.productsore.usersApi.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    private final AuthenticationService authenticationService;

    public WalletService(WalletRepository walletRepository, AuthenticationService authenticationService) {
        this.walletRepository = walletRepository;
        this.authenticationService = authenticationService;
    }

    public WalletViewDTO getWallet() {
        User currentUser = authenticationService.currentUser();
        Wallet wallet = findWalletByUser(currentUser);
        WalletViewDTO dto = new WalletViewDTO(new UserDTO(wallet.getUser().getId(), wallet.getUser().getUsername()), wallet.getBalance());;
        return dto;
    }

    public void save(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        wallet.setBalance(walletDTO.balance());
        wallet.setUser(walletDTO.user());

        walletRepository.save(wallet);
    }

    public void depositUpdateBalance(Integer price) {
        User currentUser = authenticationService.currentUser();
        Wallet wallet = findWalletByUser(currentUser);
        wallet.setBalance(wallet.getBalance() + price);

        walletRepository.save(wallet);
    }

    public void buymentUpdateBalance(Integer price) {
        User currentUser = authenticationService.currentUser();
        Wallet wallet = findWalletByUser(currentUser);
        int decreasedPrice = wallet.getBalance() - price;
        if (decreasedPrice < 0) {
            throw new BalanceNotEnoughException();
        }
        wallet.setBalance(decreasedPrice);
        walletRepository.save(wallet);
    }

    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(WalletNotFoundException::new);
    }

    public Wallet findWalletById(UUID walletId) {
        return walletRepository.findById(walletId).orElseThrow(WalletNotFoundException::new);
    }


}
