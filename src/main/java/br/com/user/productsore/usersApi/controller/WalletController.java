package br.com.user.productsore.usersApi.controller;

import br.com.user.productsore.usersApi.domain.wallet.Wallet;
import br.com.user.productsore.usersApi.dto.DepositDTO;
import br.com.user.productsore.usersApi.dto.WalletDTO;
import br.com.user.productsore.usersApi.dto.WalletViewDTO;
import br.com.user.productsore.usersApi.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<WalletViewDTO> getWallet() {
        return ResponseEntity.ok(walletService.getWallet());
    }
    @PostMapping("/deposit")
    public ResponseEntity<Void> depositOnWallet(@RequestBody @Valid DepositDTO depositDTO) {
        walletService.depositUpdateBalance(depositDTO.price());

        return ResponseEntity.ok().build();
    }
}
