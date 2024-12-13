package br.com.user.productsore.usersApi.controller;

import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.dto.AuthenticationDTO;
import br.com.user.productsore.usersApi.dto.RegisterDTO;
import br.com.user.productsore.usersApi.dto.TokenDTO;
import br.com.user.productsore.usersApi.dto.WalletDTO;
import br.com.user.productsore.usersApi.repository.UserRepository;
import br.com.user.productsore.usersApi.service.TokenService;
import br.com.user.productsore.usersApi.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final TokenService tokenService;

    final WalletService walletService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, WalletService walletService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.walletService = walletService;
    }

    @PostMapping("/login")
    ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernameAndPassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernameAndPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.role(), data.username(), encryptedPassword);

        this.userRepository.save(user);
        walletService.save(new WalletDTO(user, 0));

        return ResponseEntity.ok().build();
    }

}
