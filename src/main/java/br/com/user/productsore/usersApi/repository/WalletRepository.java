package br.com.user.productsore.usersApi.repository;

import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByUser(User user);
}
